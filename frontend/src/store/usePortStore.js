import axios from "axios";
import { create } from "zustand";

const API_BASE = import.meta.env.VITE_API_URL || "http://localhost:8080";
const apiClient = axios.create({
  baseURL: API_BASE,
  withCredentials: true,
  headers: { "Content-Type": "application/json" },
});

const demoVessels = [
  {
    id: 1,
    vesselName: "MV Atlantic Star",
    status: "APPROACHING",
    arrivalEta: "2026-08-10T14:30:00",
    assignedBerth: { berthName: "Berth B-07" },
  },
  {
    id: 2,
    vesselName: "MV Harbor Wave",
    status: "DOCKED",
    arrivalEta: "2026-08-10T11:00:00",
    assignedBerth: { berthName: "Berth C-03" },
  },
  {
    id: 3,
    vesselName: "MV North Breeze",
    status: "DEPARTED",
    arrivalEta: "2026-08-09T22:15:00",
    assignedBerth: null,
  },
];

const demoBerths = [
  {
    id: 1,
    berthName: "Berth A-01",
    status: "AVAILABLE",
    capacityLength: 320,
    capacityDepth: 14,
  },
  {
    id: 2,
    berthName: "Berth B-07",
    status: "OCCUPIED",
    capacityLength: 280,
    capacityDepth: 12,
  },
  {
    id: 3,
    berthName: "Berth D-04",
    status: "MAINTENANCE",
    capacityLength: 250,
    capacityDepth: 10,
  },
];

const demoContainers = [
  {
    id: 1,
    containerId: "CNT-1001",
    cargoType: "Refrigerated",
    status: "IN_TRANSIT",
    assignedYardLocation: "Yard 2A",
  },
  {
    id: 2,
    containerId: "CNT-1002",
    cargoType: "Bulk",
    status: "LOADED",
    assignedYardLocation: "Block 5",
  },
  {
    id: 3,
    containerId: "CNT-1003",
    cargoType: "Hazmat",
    status: "UNLOADED",
    assignedYardLocation: "Block 1",
  },
];

const initialFormState = {
  vesselId: "",
  vesselName: "",
  shipType: "",
  length: "",
  draftDepth: "",
  arrivalEta: "",
  departureEtd: "",
  status: "APPROACHING",
  assignedBerthId: "",
};

const initialBerthForm = {
  berthId: "",
  berthName: "",
  capacityLength: "",
  capacityDepth: "",
  status: "AVAILABLE",
};

const initialContainerForm = {
  containerId: "",
  weight: "",
  cargoType: "",
  assignedVesselId: "",
  assignedYardLocation: "",
  status: "IN_TRANSIT",
};

const usePortStore = create((set, get) => ({
  isAuthenticated: false,
  authMessage: "",
  authForm: { identifier: "", password: "" },
  admin: null,
  apiStatus: "demo",
  vessels: demoVessels,
  berths: demoBerths,
  containers: demoContainers,
  formState: initialFormState,
  berthForm: initialBerthForm,
  containerForm: initialContainerForm,
  stats: {
    totalVessels: demoVessels.length,
    availableBerths: demoBerths.filter((berth) => berth.status === "AVAILABLE")
      .length,
    occupiedBerths: demoBerths.filter((berth) => berth.status === "OCCUPIED")
      .length,
    inTransitContainers: demoContainers.filter(
      (container) => container.status === "IN_TRANSIT",
    ).length,
  },

  setAuthForm: (payload) => set({ authForm: payload }),
  setFormState: (payload) => set({ formState: payload }),
  setBerthForm: (payload) => set({ berthForm: payload }),
  setContainerForm: (payload) => set({ containerForm: payload }),
  setAuthMessage: (message) => set({ authMessage: message }),
  setAdmin: (admin) => set({ admin }),
  setAuthenticated: (value) => set({ isAuthenticated: value }),

  fetchJson: async (path, options = {}) => {
    const { body, data, headers = {}, ...restOptions } = options;
    const requestData = data ?? body;

    try {
      const response = await apiClient.request({
        url: path,
        ...restOptions,
        headers: {
          ...headers,
          ...(requestData !== undefined
            ? { "Content-Type": "application/json" }
            : {}),
        },
        data: requestData,
      });
      return response.data;
    } catch (error) {
      throw new Error(
        error.response?.data?.message || error.message || "Request failed",
      );
    }
  },

  loadDashboard: async () => {
    try {
      const [vesselResponse, berthResponse, containerResponse] =
        await Promise.all([
          get().fetchJson("/api/vessels"),
          get().fetchJson("/api/berths"),
          get().fetchJson("/api/containers"),
        ]);

      const nextVessels = vesselResponse.data || [];
      const nextBerths = berthResponse.data || [];
      const nextContainers = containerResponse.data || [];

      set({
        vessels: nextVessels,
        berths: nextBerths,
        containers: nextContainers,
        apiStatus: "live",
        authMessage: "Connected to backend data.",
        stats: {
          totalVessels: nextVessels.length,
          availableBerths: nextBerths.filter(
            (berth) => berth.status === "AVAILABLE",
          ).length,
          occupiedBerths: nextBerths.filter(
            (berth) => berth.status === "OCCUPIED",
          ).length,
          inTransitContainers: nextContainers.filter(
            (container) => container.status === "IN_TRANSIT",
          ).length,
        },
      });
    } catch (error) {
      set({
        vessels: demoVessels,
        berths: demoBerths,
        containers: demoContainers,
        apiStatus: "demo",
        authMessage: "Backend unavailable, showing polished demo data.",
        stats: {
          totalVessels: demoVessels.length,
          availableBerths: demoBerths.filter(
            (berth) => berth.status === "AVAILABLE",
          ).length,
          occupiedBerths: demoBerths.filter(
            (berth) => berth.status === "OCCUPIED",
          ).length,
          inTransitContainers: demoContainers.filter(
            (container) => container.status === "IN_TRANSIT",
          ).length,
        },
      });
    }
  },

  login: async (authForm) => {
    try {
      const payload = await get().fetchJson(
        "/auth/admin/port-management/login",
        {
          method: "POST",
          data: authForm,
        },
      );
      set({
        admin: payload.data,
        isAuthenticated: true,
        authMessage: "Signed in successfully.",
      });
      await get().loadDashboard();
    } catch (error) {
      set({
        admin: { fullName: "Demo Administrator", department: "Operations" },
        isAuthenticated: true,
        authMessage: error.message || "Login preview enabled.",
      });
    }
  },

  createVessel: async (event) => {
    event.preventDefault();
    const { formState } = get();
    try {
      await get().fetchJson("/api/vessels", {
        method: "POST",
        data: {
          vesselId: formState.vesselId,
          vesselName: formState.vesselName,
          shipType: formState.shipType,
          length: Number(formState.length),
          draftDepth: Number(formState.draftDepth),
          arrivalEta: formState.arrivalEta
            ? new Date(formState.arrivalEta).toISOString()
            : null,
          departureEtd: formState.departureEtd
            ? new Date(formState.departureEtd).toISOString()
            : null,
          status: formState.status,
          assignedBerthId: formState.assignedBerthId
            ? Number(formState.assignedBerthId)
            : null,
        },
      });
      set({
        formState: initialFormState,
        authMessage: "Vessel registered successfully.",
      });
      await get().loadDashboard();
    } catch (error) {
      set({ authMessage: error.message });
    }
  },

  createBerth: async (event) => {
    event.preventDefault();
    const { berthForm } = get();
    try {
      await get().fetchJson("/api/berths", {
        method: "POST",
        data: {
          berthId: berthForm.berthId,
          berthName: berthForm.berthName,
          capacityLength: Number(berthForm.capacityLength),
          capacityDepth: Number(berthForm.capacityDepth),
          status: berthForm.status,
        },
      });
      set({
        berthForm: initialBerthForm,
        authMessage: "Berth created successfully.",
      });
      await get().loadDashboard();
    } catch (error) {
      set({ authMessage: error.message });
    }
  },

  createContainer: async (event) => {
    event.preventDefault();
    const { containerForm } = get();
    try {
      await get().fetchJson("/api/containers", {
        method: "POST",
        data: {
          containerId: containerForm.containerId,
          weight: Number(containerForm.weight),
          cargoType: containerForm.cargoType,
          assignedVesselId: containerForm.assignedVesselId
            ? Number(containerForm.assignedVesselId)
            : null,
          assignedYardLocation: containerForm.assignedYardLocation,
          status: containerForm.status,
        },
      });
      set({
        containerForm: initialContainerForm,
        authMessage: "Container logged successfully.",
      });
      await get().loadDashboard();
    } catch (error) {
      set({ authMessage: error.message });
    }
  },

  signOut: () => {
    set({ isAuthenticated: false, admin: null, authMessage: "Signed out." });
  },
}));

export default usePortStore;
