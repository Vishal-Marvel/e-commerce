// eslint-disable-next-line no-unused-vars
import React, { useState } from "react";

export default function Count(pros) {
  let [count, setCount] = useState(pros.inx);

  function incrementCount() {
    count = count + 1;
    setCount(count);
  }
  function decrementCount() {
    if (count === 1) {
      return;
    }
    count = count - 1;
    setCount(count);
  }
  return (
    <div className="flex bg-gray-300 rounded h-10 w-32 justify-center mb-10">
      <button onClick={decrementCount} className="font-bold text-2xl px-3 ">
        -
      </button>
      <h1 className="border-r-4 border-l-4 border-solid border-white px-3 pt-1 text-xl">
        {count}
      </h1>
      <button onClick={incrementCount} className="font-bold text-2xl px-3 ">
        +
      </button>
         
    </div>
  );
}
