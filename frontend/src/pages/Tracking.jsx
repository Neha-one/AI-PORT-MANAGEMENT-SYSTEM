function Tracking() {

  const steps = [
    "Booking Confirmed",
    "Container Loaded",
    "Ship Departed",
    "Reached Destination Port",
    "Delivered"
  ];


  return (
    <div className="space-y-6">

      <h1 className="text-3xl font-bold">
        Shipment Tracking
      </h1>


      <div className="bg-white p-6 rounded-xl shadow-md">

        <h2 className="text-xl font-bold mb-4">
          Container #CN001
        </h2>


        <p>
          Current Location: Mumbai Port
        </p>

        <p>
          Destination: Dubai Port
        </p>

        <p className="text-green-600 mt-2">
          Status: In Transit
        </p>


      </div>



      <div className="bg-white p-6 rounded-xl shadow-md">

        <h2 className="text-xl font-bold mb-5">
          Shipment Timeline
        </h2>


        <ul className="space-y-4">


          {
            steps.map((step, index) => (

              <li
                key={index}
                className="flex gap-3"
              >

                <span className="h-5 w-5 bg-blue-600 rounded-full"></span>

                <p>
                  {step}
                </p>

              </li>

            ))
          }


        </ul>


      </div>


    </div>
  )
}


export default Tracking;