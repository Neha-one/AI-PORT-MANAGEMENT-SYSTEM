import usePortStore from "../store/usePortStore";
import RouteLinks from "./RouteLinks";

function DashboardLayout({ children }) {
  const admin = usePortStore((state) => state.admin);
  const apiStatus = usePortStore((state) => state.apiStatus);
  const authMessage = usePortStore((state) => state.authMessage);
  const signOut = usePortStore((state) => state.signOut);
  const totalVessels = usePortStore((state) => state.vessels.length);
  const availableBerths = usePortStore(
    (state) =>
      state.berths.filter((berth) => berth.status === "AVAILABLE").length,
  );
  const occupiedBerths = usePortStore(
    (state) =>
      state.berths.filter((berth) => berth.status === "OCCUPIED").length,
  );
  const inTransitContainers = usePortStore(
    (state) =>
      state.containers.filter((container) => container.status === "IN_TRANSIT")
        .length,
  );

  return (
    <div className="app-shell">
      <div className="topbar panel">
        <div className="brand-block">
          <div className="brand-icon">⚓</div>
          <div>
            <h1 className="hero-title">Port Command Center</h1>
            <p className="hero-subtitle">
              AI-driven port operations, orchestrated in real time
            </p>
          </div>
        </div>
        <div
          style={{
            display: "flex",
            gap: "10px",
            alignItems: "center",
            flexWrap: "wrap",
          }}
        >
          <div className="meta-pill">{admin?.fullName || "Administrator"}</div>
          <div className="meta-pill">{admin?.department || "Operations"}</div>
          <div
            className={`status-chip ${apiStatus === "live" ? "success" : "warning"}`}
          >
            {apiStatus === "live" ? "Live sync" : "Demo mode"}
          </div>
          <button className="secondary-btn" onClick={signOut}>
            Sign out
          </button>
        </div>
      </div>

      <div className="stats-grid">
        <div className="card">
          <h3>Active vessels</h3>
          <div className="value">{totalVessels}</div>
          <div className="hint">Tracked in the system</div>
        </div>
        <div className="card">
          <h3>Available berths</h3>
          <div className="value">{availableBerths}</div>
          <div className="hint">Ready for docking</div>
        </div>
        <div className="card">
          <h3>Occupied berths</h3>
          <div className="value">{occupiedBerths}</div>
          <div className="hint">Under active operations</div>
        </div>
        <div className="card">
          <h3>In-transit containers</h3>
          <div className="value">{inTransitContainers}</div>
          <div className="hint">Moving through the terminal</div>
        </div>
      </div>

      <RouteLinks />

      {authMessage ? (
        <div className="status-banner" style={{ marginBottom: "16px" }}>
          {authMessage}
        </div>
      ) : null}
      {children}
    </div>
  );
}

export default DashboardLayout;
