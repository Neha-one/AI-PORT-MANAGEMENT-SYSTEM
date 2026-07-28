function Navbar() {
  return (
    <nav className="h-16 bg-slate-900 text-white flex items-center justify-between px-6 shadow-md">

      <h2 className="text-xl font-bold">
        AI PORT MANAGEMENT SYSTEM
      </h2>


      <div className="flex items-center gap-4">

        <input
          type="text"
          placeholder="Search..."
          className="px-3 py-2 rounded-md text-black outline-none"
        />


        <button className="text-xl">
          🔔
        </button>


        <button className="bg-blue-600 px-4 py-2 rounded-md hover:bg-blue-700">
          Profile
        </button>

      </div>

    </nav>
  );
}

export default Navbar;
