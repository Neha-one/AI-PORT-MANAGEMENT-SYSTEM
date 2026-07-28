function Reports() {

  return (

    <div className="space-y-6">


      <h1 className="text-3xl font-bold">
        Reports & Analytics
      </h1>



      <div className="grid md:grid-cols-3 gap-6">


        <div className="bg-white p-6 rounded-xl shadow">
          <h2>Total Shipments</h2>
          <p className="text-3xl font-bold">
            1200
          </p>
        </div>



        <div className="bg-white p-6 rounded-xl shadow">
          <h2>Monthly Revenue</h2>
          <p className="text-3xl font-bold">
            $25000
          </p>
        </div>



        <div className="bg-white p-6 rounded-xl shadow">
          <h2>Success Rate</h2>
          <p className="text-3xl font-bold">
            95%
          </p>
        </div>


      </div>



    </div>

  )

}


export default Reports;