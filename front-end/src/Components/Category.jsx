import React from 'react'

function CatRepeat(props) {
  return (
    <button className='hover:text-white hover:bg-black border-black border-solid px-8 py-1 border rounded-full font-semibold text-xl ml-14'>
      {props.cat}
    </button>
  );
}

export default function Category() {
  return (
    <div className='w-screen px-12 py-10 text-center mt-16 border-y-4 border-gray-300'>
      <button className='border-black bg-black text-white border-solid px-8 py-1 border rounded-full font-semibold text-xl'>
        All Category
      </button>
      <CatRepeat cat="Clothes" />
      <CatRepeat cat="Electronics" />
      <CatRepeat cat="Home Decor" />
      <CatRepeat cat="Leather purse" />
    </div>
  )
}