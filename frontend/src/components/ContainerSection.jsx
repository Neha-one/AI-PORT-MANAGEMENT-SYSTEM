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

function ContainerSection() {
  const containerForm = usePortStore((state) => state.containerForm);
  const containers = usePortStore((state) => state.containers);
  const setContainerForm = usePortStore((state) => state.setContainerForm);
  const createContainer = usePortStore((state) => state.createContainer);

  return (
    <div className="dashboard-grid">
      <div className="panel">
        <div className="panel-header">
          <h2>Log container</h2>
          <span className="status-chip">Cargo</span>
        </div>
        <form onSubmit={createContainer} className="form-grid">
          <div className="form-row">
            <label>
              Container ID
              <input
                value={containerForm.containerId}
                onChange={(event) =>
                  setContainerForm({
                    ...containerForm,
                    containerId: event.target.value,
                  })
                }
                required
              />
            </label>
            <label>
              Weight
              <input
                type="number"
                value={containerForm.weight}
                onChange={(event) =>
                  setContainerForm({
                    ...containerForm,
                    weight: event.target.value,
                  })
                }
                required
              />
            </label>
          </div>
          <div className="form-row">
            <label>
              Cargo type
              <input
                value={containerForm.cargoType}
                onChange={(event) =>
                  setContainerForm({
                    ...containerForm,
                    cargoType: event.target.value,
                  })
                }
                required
              />
            </label>
            <label>
              Status
              <select
                value={containerForm.status}
                onChange={(event) =>
                  setContainerForm({
                    ...containerForm,
                    status: event.target.value,
                  })
                }
              >
                <option value="IN_TRANSIT">IN_TRANSIT</option>
                <option value="LOADED">LOADED</option>
                <option value="UNLOADED">UNLOADED</option>
              </select>
            </label>
          </div>
          <div className="form-row">
            <label>
              Assigned vessel ID
              <input
                value={containerForm.assignedVesselId}
                onChange={(event) =>
                  setContainerForm({
                    ...containerForm,
                    assignedVesselId: event.target.value,
                  })
                }
                placeholder="Optional"
              />
            </label>
            <label>
              Yard location
              <input
                value={containerForm.assignedYardLocation}
                onChange={(event) =>
                  setContainerForm({
                    ...containerForm,
                    assignedYardLocation: event.target.value,
                  })
                }
                placeholder="Optional"
              />
            </label>
          </div>
          <div className="form-actions">
            <button className="primary-btn" type="submit">
              Log container
            </button>
          </div>
        </form>
      </div>

      <div className="panel">
        <div className="panel-header">
          <h3>Container manifest</h3>
          <span className="status-chip">{containers.length} records</span>
        </div>
        <div className="table-wrapper">
          {containers.length ? (
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Cargo</th>
                  <th>Status</th>
                  <th>Location</th>
                </tr>
              </thead>
              <tbody>
                {containers.map((item) => (
                  <tr key={item.id}>
                    <td>{item.containerId}</td>
                    <td>{item.cargoType}</td>
                    <td>
                      <span
                        className={`status-chip ${statusTone(item.status)}`}
                      >
                        {item.status}
                      </span>
                    </td>
                    <td>{item.assignedYardLocation || "—"}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <div className="empty-state">No containers yet.</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default ContainerSection;
