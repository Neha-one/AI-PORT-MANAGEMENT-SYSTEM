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

function BerthSection() {
  const berthForm = usePortStore((state) => state.berthForm);
  const berths = usePortStore((state) => state.berths);
  const setBerthForm = usePortStore((state) => state.setBerthForm);
  const createBerth = usePortStore((state) => state.createBerth);

  return (
    <div className="dashboard-grid">
      <div className="panel">
        <div className="panel-header">
          <h2>Create berth</h2>
          <span className="status-chip">Docking</span>
        </div>
        <form onSubmit={createBerth} className="form-grid">
          <div className="form-row">
            <label>
              Berth ID
              <input
                value={berthForm.berthId}
                onChange={(event) =>
                  setBerthForm({ ...berthForm, berthId: event.target.value })
                }
                required
              />
            </label>
            <label>
              Berth name
              <input
                value={berthForm.berthName}
                onChange={(event) =>
                  setBerthForm({ ...berthForm, berthName: event.target.value })
                }
                required
              />
            </label>
          </div>
          <div className="form-row">
            <label>
              Capacity length
              <input
                type="number"
                value={berthForm.capacityLength}
                onChange={(event) =>
                  setBerthForm({
                    ...berthForm,
                    capacityLength: event.target.value,
                  })
                }
                required
              />
            </label>
            <label>
              Capacity depth
              <input
                type="number"
                value={berthForm.capacityDepth}
                onChange={(event) =>
                  setBerthForm({
                    ...berthForm,
                    capacityDepth: event.target.value,
                  })
                }
                required
              />
            </label>
          </div>
          <label>
            Status
            <select
              value={berthForm.status}
              onChange={(event) =>
                setBerthForm({ ...berthForm, status: event.target.value })
              }
            >
              <option value="AVAILABLE">AVAILABLE</option>
              <option value="OCCUPIED">OCCUPIED</option>
              <option value="MAINTENANCE">MAINTENANCE</option>
            </select>
          </label>
          <div className="form-actions">
            <button className="primary-btn" type="submit">
              Create berth
            </button>
          </div>
        </form>
      </div>

      <div className="panel">
        <div className="panel-header">
          <h3>Berth availability</h3>
          <span className="status-chip">{berths.length} configured</span>
        </div>
        <div className="table-wrapper">
          {berths.length ? (
            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Status</th>
                  <th>Length</th>
                  <th>Depth</th>
                </tr>
              </thead>
              <tbody>
                {berths.map((item) => (
                  <tr key={item.id}>
                    <td>{item.berthName}</td>
                    <td>
                      <span
                        className={`status-chip ${statusTone(item.status)}`}
                      >
                        {item.status}
                      </span>
                    </td>
                    <td>{item.capacityLength}</td>
                    <td>{item.capacityDepth}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <div className="empty-state">No berths yet.</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default BerthSection;
