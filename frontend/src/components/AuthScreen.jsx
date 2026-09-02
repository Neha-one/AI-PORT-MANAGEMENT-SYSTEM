import usePortStore from "../store/usePortStore";

function AuthScreen() {
  const authForm = usePortStore((state) => state.authForm);
  const authMessage = usePortStore((state) => state.authMessage);
  const setAuthForm = usePortStore((state) => state.setAuthForm);
  const login = usePortStore((state) => state.login);

  const handleSubmit = (event) => {
    event.preventDefault();
    login(authForm);
  };

  return (
    <div className="app-shell">
      <div className="auth-card">
        <div className="brand-block" style={{ marginBottom: "16px" }}>
          <div className="brand-icon">⚓</div>

          <div>
            <h1>Port Command Center</h1>

            <p className="hero-subtitle">
              AI-powered berth, vessel, and container operations
            </p>
          </div>
        </div>

        <p>
          Sign in with your administrator credentials to manage the terminal.
        </p>

        <form
          onSubmit={handleSubmit}
          className="form-grid"
          style={{ marginTop: "18px" }}
        >
          <label>
            Identifier

            <input
              value={authForm.identifier}
              onChange={(event) =>
                setAuthForm({
                  ...authForm,
                  identifier: event.target.value,
                })
              }
              placeholder="Email or employee ID"
              required
            />
          </label>

          <label>
            Password

            <input
              type="password"
              value={authForm.password}
              onChange={(event) =>
                setAuthForm({
                  ...authForm,
                  password: event.target.value,
                })
              }
              placeholder="Password"
              required
            />
          </label>

          <button className="primary-btn" type="submit">
            Access dashboard
          </button>
        </form>

        {authMessage ? (
          <p className="status-banner" style={{ marginTop: "14px" }}>
            {authMessage}
          </p>
        ) : null}
      </div>
    </div>
  );
}

export default AuthScreen;