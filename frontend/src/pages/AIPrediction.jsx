function AIPrediction() {

  return (

    <div className="space-y-6">


      <h1 className="text-3xl font-bold">
        AI Delay Prediction
      </h1>



      <div className="bg-slate-900 text-white p-8 rounded-xl">


        <h2 className="text-2xl font-bold">
          AI Analysis Result
        </h2>


        <div className="mt-5 space-y-3">


          <p>
            Delay Probability:
            <span className="text-red-400">
              78%
            </span>
          </p>


          <p>
            Weather Risk:
            High
          </p>


          <p>
            Traffic Condition:
            Moderate
          </p>


          <p>
            Estimated Delay:
            12 Hours
          </p>


        </div>



        <button className="mt-6 bg-blue-600 px-5 py-3 rounded">
          Run New Prediction
        </button>


      </div>



    </div>

  )

}


export default AIPrediction;