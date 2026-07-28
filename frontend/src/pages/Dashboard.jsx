// function Dashboard() {
//   return (
//     <div className="space-y-6">

//       {/* Heading */}
//       <div>
//         <h1 className="text-3xl font-bold text-slate-800">
//           Dashboard
//         </h1>

//         <p className="text-gray-500">
//           Welcome to AI Port Management System
//         </p>
//       </div>


//       {/* Cards */}
//       <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">


//         <div className="bg-white p-6 rounded-xl shadow-md">
//           <h2 className="text-gray-500">
//             Total Ships
//           </h2>

//           <p className="text-3xl font-bold text-blue-600 mt-2">
//             120
//           </p>
//         </div>



//         <div className="bg-white p-6 rounded-xl shadow-md">
//           <h2 className="text-gray-500">
//             Containers
//           </h2>

//           <p className="text-3xl font-bold text-green-600 mt-2">
//             850
//           </p>
//         </div>



//         <div className="bg-white p-6 rounded-xl shadow-md">
//           <h2 className="text-gray-500">
//             Active Bookings
//           </h2>

//           <p className="text-3xl font-bold text-purple-600 mt-2">
//             75
//           </p>
//         </div>



//         <div className="bg-white p-6 rounded-xl shadow-md">
//           <h2 className="text-gray-500">
//             Delayed Ships
//           </h2>

//           <p className="text-3xl font-bold text-red-600 mt-2">
//             12
//           </p>
//         </div>


//       </div>



//       {/* AI Prediction */}
//       {/* <div className="bg-slate-900 text-white rounded-xl p-6 shadow-md">

//         <h2 className="text-xl font-bold">
//          AI Delay Prediction
//         </h2>


//         <p className="mt-3 text-gray-300">
//           AI predicts possible delays based on weather,
//           traffic and previous ship data.
//         </p>


//         <button className="mt-4 bg-blue-600 px-5 py-2 rounded-lg hover:bg-blue-700">
//           View Prediction
//         </button>

//       </div> */}



//       {/* Recent Activity */}
//       <div className="bg-white rounded-xl shadow-md p-6">

//         <h2 className="text-xl font-bold mb-4">
//           Recent Activity
//         </h2>


//         <ul className="space-y-3">

//           <li className="border-b pb-2">
//             Ship Ocean Star arrived at Port
//           </li>

//           <li className="border-b pb-2">
//             Container #4582 delivered
//           </li>

//           <li>
//             Weather alert detected
//           </li>

//         </ul>

//       </div>


//     </div>
//   );
// }

// export default Dashboard;

import Card from "../components/Card";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from "recharts";


function Dashboard() {


  const data = [
    {
      month: "Jan",
      ships: 40
    },
    {
      month: "Feb",
      ships: 60
    },
    {
      month: "Mar",
      ships: 80
    },
    {
      month: "Apr",
      ships: 55
    }
  ];


  return (

    <div className="space-y-6">


      <h1 className="text-3xl font-bold">
        Dashboard
      </h1>



      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">

        <Card
          title="Total Ships"
          value="120"
          color="text-blue-600"
        />

        <Card
          title="Containers"
          value="850"
          color="text-green-600"
        />

        <Card
          title="Bookings"
          value="75"
          color="text-purple-600"
        />

        <Card
          title="Delays"
          value="12"
          color="text-red-600"
        />

      </div>



      <div className="bg-white p-6 rounded-xl shadow-md">


        <h2 className="text-xl font-bold mb-5">
          Ship Traffic
        </h2>


        <ResponsiveContainer width="100%" height={300}>

          <LineChart data={data}>

            <XAxis dataKey="month" />

            <YAxis />

            <Tooltip />

            <Line
              type="monotone"
              dataKey="ships"
              stroke="#2563eb"
            />

          </LineChart>


        </ResponsiveContainer>


      </div>


    </div>

  )
}


export default Dashboard;