function Setting() {

  return (

    <div className="space-y-6">


      <h1 className="text-3xl font-bold">
        Settings
      </h1>



      <div className="bg-white p-6 rounded-xl shadow-md space-y-5">


        <div className="flex justify-between">

          <p>
            Dark Mode
          </p>

          <input type="checkbox" />

        </div>



        <div className="flex justify-between">

          <p>
            Notifications
          </p>

          <input type="checkbox" />

        </div>



        <button className="bg-red-600 text-white px-5 py-2 rounded">
          Logout
        </button>


      </div>



    </div>

  )

}


export default Setting;