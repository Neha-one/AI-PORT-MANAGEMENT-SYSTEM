function Container() {


  const containers = [
    {
      id: "CN001",
      owner: "ABC Logistics",
      weight: "20 Ton",
      status: "In Transit"
    },

    {
      id: "CN002",
      owner: "XYZ Shipping",
      weight: "15 Ton",
      status: "Delivered"
    }

  ];


  return (

    <div className="space-y-6">


      <h1 className="text-3xl font-bold">
        Container Management
      </h1>


      <input
        placeholder="Search Container"
        className="border p-3 rounded-lg w-full"
      />



      <div className="grid md:grid-cols-2 gap-6">


        {
          containers.map((item) => (


            <div
              key={item.id}
              className="bg-white p-6 rounded-xl shadow-md"
            >


              <h2 className="text-xl font-bold">
                {item.id}
              </h2>


              <p>
                Owner: {item.owner}
              </p>


              <p>
                Weight: {item.weight}
              </p>


              <p className="text-green-600">
                {item.status}
              </p>



            </div>


          ))
        }


      </div>


    </div>


  )

}


export default Container;