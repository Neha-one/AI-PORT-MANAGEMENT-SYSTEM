import { useEffect } from "react";
import { Navigate, Route, Routes } from "react-router-dom";

import "./styles.css";

import AuthScreen from "./components/AuthScreen";
import DashboardLayout from "./components/DashboardLayout";
import OverviewSection from "./components/OverviewSection";
import VesselSection from "./components/VesselSection";
import BerthSection from "./components/BerthSection";
import ContainerSection from "./components/ContainerSection";

import usePortStore from "./store/usePortStore";

function App() {
  const loadDashboard = usePortStore(
    (state) => state.loadDashboard,
  );

  const isAuthenticated = usePortStore(
    (state) => state.isAuthenticated,
  );

  useEffect(() => {
    if (isAuthenticated) {
      loadDashboard();
    }
  }, [isAuthenticated, loadDashboard]);

  return (
    <Routes>
      {/* ========================= */}
      {/* LOGIN / ROOT */}
      {/* ========================= */}

      <Route
        path="/"
        element={
          isAuthenticated ? (
            <Navigate to="/overview" replace />
          ) : (
            <AuthScreen />
          )
        }
      />

      {/* ========================= */}
      {/* OVERVIEW */}
      {/* ========================= */}

      <Route
        path="/overview"
        element={
          isAuthenticated ? (
            <DashboardLayout>
              <OverviewSection />
            </DashboardLayout>
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      {/* ========================= */}
      {/* VESSELS */}
      {/* ========================= */}

      <Route
        path="/vessels"
        element={
          isAuthenticated ? (
            <DashboardLayout>
              <VesselSection />
            </DashboardLayout>
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      {/* ========================= */}
      {/* BERTHS */}
      {/* ========================= */}

      <Route
        path="/berths"
        element={
          isAuthenticated ? (
            <DashboardLayout>
              <BerthSection />
            </DashboardLayout>
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      {/* ========================= */}
      {/* CONTAINERS */}
      {/* ========================= */}

      <Route
        path="/containers"
        element={
          isAuthenticated ? (
            <DashboardLayout>
              <ContainerSection />
            </DashboardLayout>
          ) : (
            <Navigate to="/" replace />
          )
        }
      />

      {/* ========================= */}
      {/* UNKNOWN ROUTE */}
      {/* ========================= */}

      <Route
        path="*"
        element={<Navigate to="/" replace />}
      />
    </Routes>
  );
}

export default App;