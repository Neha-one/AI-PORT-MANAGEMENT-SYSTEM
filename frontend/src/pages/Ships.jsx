function Ships() {

  const ships = [
    {
      id: 1,
      name: "Ocean Star",
      capacity: "5000 TEU",
      status: "Active"
    },
    {
      id: 2,
      name: "Sea Queen",
      capacity: "3000 TEU",
      status: "Inactive"
    }
  ];


  return (

    <div className="space-y-6">


      <h1 className="text-3xl font-bold">
        Ships Management
      </h1>


      <button className="bg-blue-600 text-white px-5 py-2 rounded-lg">
        Add Ship
      </button>



      <div className="bg-white shadow-md rounded-xl overflow-hidden">


        <table className="w-full">


          <thead className="bg-slate-800 text-white">

            <tr>

              <th className="p-3">
                Name
              </th>

              <th className="p-3">
                Capacity
              </th>

              <th className="p-3">
                Status
              </th>

              <th className="p-3">
                Action
              </th>


            </tr>

          </thead>



          <tbody>


            {
              ships.map((ship) => (

                <tr
                  key={ship.id}
                  className="border-b"
                >


                  <td className="p-3">
                    {ship.name}
                  </td>


                  <td className="p-3">
                    {ship.capacity}
                  </td>


                  <td className="p-3">
                    {ship.status}
                  </td>


                  <td className="p-3">

                    <button className="text-blue-600 mr-3">
                      Edit
                    </button>


                    <button className="text-red-600">
                      Delete
                    </button>

                  </td>


                </tr>

              ))
            }


          </tbody>


        </table>


      </div>


    </div>

  )

}


export default Ships;