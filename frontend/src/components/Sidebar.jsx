import { NavLink } from "react-router-dom";


function Sidebar() {

  const menuItems = [
    { name: "Dashboard", path: "/dashboard" },
    { name: "Ships", path: "/ships" },
    { name: "Container", path: "/container" },
    { name: "Booking", path: "/booking" },
    { name: "Tracking", path: "/tracking" },
    { name: "AI Prediction", path: "/ai" },
    { name: "Reports", path: "/reports" },
    { name: "Profile", path: "/profile" },
    { name: "Setting", path: "/setting" },
  ];


  return (

    <aside className="w-64 h-screen bg-slate-800 text-white p-5">

      <h2 className="text-xl font-bold mb-6">
        Menu
      </h2>


      <ul className="space-y-3">

        {
          menuItems.map((item) => (

            <li key={item.path}>

              <NavLink
                to={item.path}
                className={({ isActive }) =>
                  `block px-4 py-2 rounded-md 
                  ${isActive
                    ? "bg-blue-600"
                    : "hover:bg-slate-700"
                  }`
                }
              >
                {item.name}
              </NavLink>

            </li>

          ))
        }

      </ul>

    </aside>

  );
}


export default Sidebar;