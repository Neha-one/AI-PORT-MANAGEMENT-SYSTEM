import { useState } from "react";


function Booking() {


  const [booking, setBooking] = useState({

    container: "",
    destination: "",
    date: "",
    weight: ""

  });


  function handleChange(e) {

    setBooking({
      ...booking,
      [e.target.name]: e.target.value
    })

  }



  function handleSubmit(e) {

    e.preventDefault();

    console.log(booking);

  }



  return (

    <div>


      <h1 className="text-3xl font-bold mb-6">
        Create Booking
      </h1>



      <form
        onSubmit={handleSubmit}
        className="bg-white p-6 rounded-xl shadow-md space-y-4"
      >



        <input
          name="container"
          placeholder="Container Number"
          className="border p-3 w-full rounded"
          onChange={handleChange}
        />



        <input
          name="destination"
          placeholder="Destination Port"
          className="border p-3 w-full rounded"
          onChange={handleChange}
        />



        <input
          name="weight"
          placeholder="Cargo Weight"
          className="border p-3 w-full rounded"
          onChange={handleChange}
        />



        <input
          name="date"
          type="date"
          className="border p-3 w-full rounded"
          onChange={handleChange}
        />



        <button
          className="bg-blue-600 text-white px-6 py-3 rounded"
        >
          Submit Booking
        </button>



      </form>



    </div>

  )

}


export default Booking;