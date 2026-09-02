import usePortStore from "../store/usePortStore";

const statusTone = (value) => {
  if (!value) return "neutral";
  const normalized = String(value).toUpperCase();
  if (
    normalized === "AVAILABLE" ||
    normalized === "DOCKED" ||
    normalized === "LOADED"
  )
    return "success";
  if (
    normalized === "APPROACHING" ||
    normalized === "IN_TRANSIT" ||
    normalized === "OCCUPIED"
  )
    return "warning";
  return "neutral";
};

const formatDate = (value) => {
  if (!value) return "—";
  const date = new Date(value);
  return Number.isNaN(date.getTime()) ? value : date.toLocaleString();
};

function OverviewSection() {
  const vessels = usePortStore((state) => state.vessels);
  const berths = usePortStore((state) => state.berths);
  const stats = {
    availableBerths: usePortStore
      .getState()
      .berths.filter((berth) => berth.status === "AVAILABLE").length,
    inTransitContainers: usePortStore
      .getState()
      .containers.filter((container) => container.status === "IN_TRANSIT")
      .length,
  };

  return (
    <div className="dashboard-grid">
      <div className="panel">
        <div className="panel-header">
          <h2>Latest vessel activity</h2>
          <span className="status-chip">Live</span>
        </div>
        <div className="table-wrapper">
          {vessels.length ? (
            <table>
              <thead>
                <tr>
                  <th>Vessel</th>
                  <th>Status</th>
                  <th>ETA</th>
                  <th>Berth</th>
                </tr>
              </thead>
              <tbody>
                {vessels.slice(0, 6).map((vessel) => (
                  <tr key={vessel.id}>
                    <td>{vessel.vesselName}</td>
                    <td>
                      <span
                        className={`status-chip ${statusTone(vessel.status)}`}
                      >
                        {vessel.status}
                      </span>
                    </td>
                    <td>{formatDate(vessel.arrivalEta)}</td>
                    <td>{vessel.assignedBerth?.berthName || "Unassigned"}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <div className="empty-state">No vessel data yet.</div>
          )}
        </div>
      </div>

      <div className="panel">
        <div className="panel-header">
          <h3>Terminal pulse</h3>
          <span className="status-chip success">Operational</span>
        </div>
        <div className="inline-list">
          <div className="inline-item">
            <span>Berths available</span>
            <strong>{stats.availableBerths}</strong>
          </div>
          <div className="inline-item">
            <span>Containers in transit</span>
            <strong>{stats.inTransitContainers}</strong>
          </div>
          <div className="inline-item">
            <span>Maintenance berths</span>
            <strong>
              {berths.filter((item) => item.status === "MAINTENANCE").length}
            </strong>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OverviewSection;
