import { Routes, Route, Navigate } from "react-router-dom";
import AuthScreen from "./AuthScreen";
import DashboardLayout from "./DashboardLayout";
import OverviewSection from "./OverviewSection";
import VesselSection from "./VesselSection";
import BerthSection from "./BerthSection";
import ContainerSection from "./ContainerSection";
import usePortStore from "../store/usePortStore";

function RouterView() {
  const isAuthenticated = usePortStore((state) => state.isAuthenticated);

  if (!isAuthenticated) {
    return <AuthScreen />;
  }

  return (
    <DashboardLayout>
      <Routes>
        <Route path="/" element={<OverviewSection />} />
        <Route path="/vessels" element={<VesselSection />} />
        <Route path="/berths" element={<BerthSection />} />
        <Route path="/containers" element={<ContainerSection />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </DashboardLayout>
  );
}

export default RouterView;
