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

function VesselSection() {
  const formState = usePortStore((state) => state.formState);
  const vessels = usePortStore((state) => state.vessels);
  const setFormState = usePortStore((state) => state.setFormState);
  const createVessel = usePortStore((state) => state.createVessel);

  return (
    <div className="dashboard-grid">
      <div className="panel">
        <div className="panel-header">
          <h2>Register vessel</h2>
          <span className="status-chip">Operations</span>
        </div>
        <form onSubmit={createVessel} className="form-grid">
          <div className="form-row">
            <label>
              Vessel ID
              <input
                value={formState.vesselId}
                onChange={(event) =>
                  setFormState({ ...formState, vesselId: event.target.value })
                }
                required
              />
            </label>
            <label>
              Vessel name
              <input
                value={formState.vesselName}
                onChange={(event) =>
                  setFormState({ ...formState, vesselName: event.target.value })
                }
                required
              />
            </label>
          </div>
          <div className="form-row">
            <label>
              Ship type
              <input
                value={formState.shipType}
                onChange={(event) =>
                  setFormState({ ...formState, shipType: event.target.value })
                }
                required
              />
            </label>
            <label>
              Status
              <select
                value={formState.status}
                onChange={(event) =>
                  setFormState({ ...formState, status: event.target.value })
                }
              >
                <option value="APPROACHING">APPROACHING</option>
                <option value="DOCKED">DOCKED</option>
                <option value="DEPARTED">DEPARTED</option>
              </select>
            </label>
          </div>
          <div className="form-row">
            <label>
              Length
              <input
                type="number"
                value={formState.length}
                onChange={(event) =>
                  setFormState({ ...formState, length: event.target.value })
                }
                required
              />
            </label>
            <label>
              Draft depth
              <input
                type="number"
                value={formState.draftDepth}
                onChange={(event) =>
                  setFormState({ ...formState, draftDepth: event.target.value })
                }
                required
              />
            </label>
          </div>
          <div className="form-row">
            <label>
              Arrival ETA
              <input
                type="datetime-local"
                value={formState.arrivalEta}
                onChange={(event) =>
                  setFormState({ ...formState, arrivalEta: event.target.value })
                }
                required
              />
            </label>
            <label>
              Departure ETD
              <input
                type="datetime-local"
                value={formState.departureEtd}
                onChange={(event) =>
                  setFormState({
                    ...formState,
                    departureEtd: event.target.value,
                  })
                }
              />
            </label>
          </div>
          <label>
            Assigned berth ID
            <input
              value={formState.assignedBerthId}
              onChange={(event) =>
                setFormState({
                  ...formState,
                  assignedBerthId: event.target.value,
                })
              }
              placeholder="Optional"
            />
          </label>
          <div className="form-actions">
            <button className="primary-btn" type="submit">
              Save vessel
            </button>
          </div>
        </form>
      </div>

      <div className="panel">
        <div className="panel-header">
          <h3>Vessel roster</h3>
          <span className="status-chip">{vessels.length} total</span>
        </div>
        <div className="table-wrapper">
          {vessels.length ? (
            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Status</th>
                  <th>Berth</th>
                </tr>
              </thead>
              <tbody>
                {vessels.map((item) => (
                  <tr key={item.id}>
                    <td>{item.vesselName}</td>
                    <td>
                      <span
                        className={`status-chip ${statusTone(item.status)}`}
                      >
                        {item.status}
                      </span>
                    </td>
                    <td>{item.assignedBerth?.berthName || "—"}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <div className="empty-state">No vessels yet.</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default VesselSection;
