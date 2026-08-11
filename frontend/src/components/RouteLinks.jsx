import { NavLink } from "react-router-dom";
import usePortStore from "../store/usePortStore";

function RouteLinks() {
  const isAuthenticated = usePortStore((state) => state.isAuthenticated);

  if (!isAuthenticated) return null;

  const links = [
    { to: "/overview", label: "Overview" },
    { to: "/vessels", label: "Vessels" },
    { to: "/berths", label: "Berths" },
    { to: "/containers", label: "Containers" },
  ];

  return (
    <div className="nav-tabs" style={{ marginBottom: "16px" }}>
      {links.map((link) => (
        <NavLink
          key={link.to}
          to={link.to}
          className={({ isActive }) => `nav-btn ${isActive ? "active" : ""}`}
        >
          {link.label}
        </NavLink>
      ))}
    </div>
  );
}

export default RouteLinks;
