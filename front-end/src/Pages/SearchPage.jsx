// eslint-disable-next-line no-unused-vars
import React, { useState } from "react";
import Card from "../Components/Card";
import Navbar from "../Components/Navbar";
import Slider from "@material-ui/core/Slider";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import Search from "../Components/DisplaySearch";
import Footer from "../Components/Footer";

const useStyles = makeStyles((theme) => ({
  root: {
    width: 300 + theme.spacing(3) * 2,
  },
  margin: {
    height: theme.spacing(3),
  },
}));

const PrettoSlider = withStyles({
  root: {
    color: "#fff",
    height: 8,
  },
  thumb: {
    height: 24,
    width: 24,
    backgroundColor: "#000",
    border: "4px solid currentColor",
    marginTop: -8,
    marginLeft: -12,
    "&:focus,&:hover,&$active": {
      boxShadow: "inherit",
    },
  },
  active: {},
  track: {
    height: 8,
    borderRadius: 1,
  },
  rail: {
    height: 8,
    borderRadius: 10,
    opacity: 1,
    color: "gray",
  },
})(Slider);

function SearchPage() {
  const [open, setOpen] = useState(false);

  const [value, setValue] = React.useState([0, 5000]);

  const rangeSelector = (event, newValue) => {
    setValue(newValue);
    console.log(newValue);
  };
  const classes = useStyles();

  return (
    <div>
      <Navbar b1="ACCOUNT" />

      <div className="mx-auto my-0 text-center justify-center items-center mt-16 ">
        <h1 className="text-2xl font-normal">
          Your search for "Jean" revealed the following:
        </h1>
        <div className="flex mx-auto my-0 justify-center items-center mt-8">
          <Search h1={"Jean"} />
          <button className="rounded text-lg font-semibold hover:text-white h-12 w-24 hover:bg-black text-black border border-gray-500  px-2 py-1">
            Search
          </button>
        </div>
      </div>

      {/*----------------------------------- Filter----------------------------------------------- */}

      <div className="mb-8">
        <button
          type="button"
          className="absolute left-28"
          onClick={() => setOpen(!open)}
        >
          ☰ Filter
        </button>
      </div>
      <div className="h-full">
        <hr className="h-px bg-white w-screen mt-1" />

        {open && (
          <div className="mr-2 pt-5 pl-5 bg-zinc-900 text-white h-max pb-10 left-28 w-80 absolute z-10 border shadow-[0_16px_24px_2px_rgba(0,0,0,0.14)] border-solid border-[rgba(0,0,0,0.04)]">
            <div className="">
              <h1 className="font-semibold text-xl ml-1">CATEGORIES</h1>
              <hr className=" h-0.5 my-1 border-0 bg-gray-500 mr-10" />
              <ul className="align-middle ">
                <li className="hover:translate-x-2">
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="Men"
                    id="men1"
                  />
                  <label htmlFor="men1" className="font-light text-md">
                    Mens
                  </label>
                </li>
                <li className="hover:translate-x-2">
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="Women"
                    id="ch2"
                  />
                  <label htmlFor="ch2" className="font-light text-md">
                    Womens
                  </label>
                </li>
                <li className="hover:translate-x-2">
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="Kids"
                    id="ch3"
                  />
                  <label htmlFor="ch3" className="font-light text-md">
                    Kids
                  </label>
                </li>
                <li className="hover:translate-x-2">
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="Boys"
                    id="ch4"
                  />
                  <label htmlFor="ch4" className="font-light text-md">
                    Boys
                  </label>
                </li>
                <li className="hover:translate-x-2">
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="Girls"
                    id="ch5"
                  />
                  <label htmlFor="ch5" className="font-light text-md">
                    Girls
                  </label>
                </li>
              </ul>
            </div>

            {/* -----------------------------Price------------------------------------ */}
            <div className="mt-10">
              <h1 className="font-semibold text-xl ml-1">PRICE</h1>
              <hr className=" h-0.5 my-1 border-0 bg-gray-500 mr-10" />

              <div className="justify-center ml-6 mt-5">
                <div className={classes.root} style={{ width: "220px" }}>
                  <PrettoSlider
                    value={value}
                    defaultValue={[0, 5000]}
                    onChange={rangeSelector}
                    size="medium"
                    max={5000}
                    min={0}
                  />
                </div>
                <div className="flex -ml-2">
                  <input
                    type="text"
                    value={`$ ${value[0]}`}
                    className="outline-none border border-white border-solid w-24 px-3 py-3 text-black text-right font-medium text-lg"
                  />
                  <h1 className="text-center my-auto mx-4 text-lg">to</h1>
                  <input
                    type="text"
                    value={`$ ${value[1]}`}
                    className="outline-none border border-white border-solid w-24 px-3 py-3 text-black text-center font-medium text-lg"
                  />
                </div>
              </div>
            </div>

            {/* -----------------------Avalability---------------------------- */}

            <div className="mt-10">
              <h1 className="font-semibold text-xl ml-1">AVAILABILITY</h1>
              <hr className=" h-0.5 my-1 border-0 bg-gray-500 mr-10" />
              <ul className="align-middle font-light text-md">
                <li>
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="In Stock"
                    id="p1"
                  />
                  <label htmlFor="p1">In Stock(6)</label>
                </li>
                <li>
                  <input
                    className="ml-4 mr-1 mt-5"
                    type="checkbox"
                    name="Out Of Stock"
                    id="p2"
                  />
                  <label htmlFor="p2">Out Of Stock(2)</label>
                </li>
              </ul>
            </div>

            {/* ----------------------------Color---------------------------------- */}

            <div className="mt-10">
              <h1 className="font-semibold text-xl ml-1">COLOR</h1>
              <hr className=" h-0.5 my-1 border-0 bg-gray-500 mr-10" />

              <div className="grid grid-cols-5 gap-4 ml-3 mr-20 mt-5">
                <button className=" bg-blue-200 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className="bg-pink-300 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-orange-100 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-slate-400 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-purple-400 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-amber-300 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-lime-200 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-white h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-green-200 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
                <button className=" bg-teal-300 h-8 w-8 rounded-full hover:scale-110 hover:border hover:border-solid hover:border-slate-400"></button>
              </div>
            </div>

            {/* ----------------------------Size---------------------------------- */}

            <div className="mt-10">
              <h1 className="font-semibold text-xl ml-1">SIZE</h1>
              <hr className=" h-0.5 my-1 border-0 bg-gray-500 mr-10" />

              <div className="grid grid-cols-4 gap-4 gap-x-0 ml-3 mr-12 mt-5 justify-center">
                <button className="h-10 w-10 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  XS
                </button>
                <button className="h-10 w-10 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  S
                </button>
                <button className="h-10 w-10 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  M
                </button>
                <button className=" h-10 w-10 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  L
                </button>
                <button className=" h-10 w-10 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  X
                </button>
                <button className=" h-10 w-10 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  XL
                </button>
                <button className=" h-10 w-12 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  2XL
                </button>
                <button className=" h-10 w-12 border border-solid border-white text-center px-2 py-2 font-medium text-md hover:bg-white hover:text-black">
                  3XL
                </button>
              </div>
              <div className="mt-10 ml-12 flex my-0">
                <button className="text-lg rounded-md font-semibold border-solid  text-white h-14 w-40 bg-zinc-900 hover:bg-white hover:text-black  border border-white  px-2 py-1">
                  Apply
                </button>
              </div>
            </div>
          </div>
        )}
      </div>

      <div className="">
        {/* ----------------------------Cards------------------------------------------------- */}
        <div>
          <Card />
          <Card />
          <Card />
        </div>
      </div>
      <div className=" flex justify-center">
        <button className=" bg-black px-4 py-2 text-white text-lg font-semibold rounded-md ">
          {" "}
          LOAD MORE{" "}
        </button>
      </div>
      <div className=" my-24 border-y-4 border-gray-300 ">
        <h1 className=" mt-4 font-medium ml-24 text-2xl text-black">
          BEST SELLERS
        </h1>
        <h1 className=" font-medium ml-24 text-2xl mb-4 text-gray-700">
          MOST SOLD ITEMS IN THE MARKET
        </h1>
      </div>
      <Card />
      <Card />
      <Card />
      <div className=" flex justify-center">
        <button className=" bg-black px-4 py-2 mb-20 text-white text-lg font-semibold rounded-md ">
          {" "}
          LOAD MORE{" "}
        </button>
      </div>
      <Footer></Footer>
    </div>
  );
}

export default SearchPage;
