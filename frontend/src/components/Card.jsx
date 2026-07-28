function Card({ title, value, color }) {
  return (
    <div className="bg-white p-6 rounded-xl shadow-md">

      <h2 className="text-gray-500">
        {title}
      </h2>

      <p className={`text-3xl font-bold mt-2 ${color}`}>
        {value}
      </p>

    </div>
  );
}

export default Card;