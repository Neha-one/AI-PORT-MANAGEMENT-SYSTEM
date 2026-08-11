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
  const loadDashboard = usePortStore((state) => state.loadDashboard);
  const isAuthenticated = usePortStore((state) => state.isAuthenticated);

  useEffect(() => {
    loadDashboard();
  }, [loadDashboard]);

  return (
    <Routes>
      <Route
        path="/"
        element={
          isAuthenticated ? <Navigate to="/overview" replace /> : <AuthScreen />
        }
      />
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
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}

export default App;
