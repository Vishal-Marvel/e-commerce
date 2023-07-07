// eslint-disable-next-line no-unused-vars
import React, { useState } from "react";
import ang1 from "../assets/j.jpg";
import ang2 from "../assets/jp1.jpg";
import ang3 from "../assets/jp2.jpg";
import main from "../assets/j.jpg";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import { useAlert } from "react-alert";
import Card from "../Components/Card";
import Tabswitch from "../Components/Tabswitch";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";

export default function Productdetail() {
  const alert = useAlert();

  let [src, setSrc] = useState(main);

  let [show4, setShow4] = useState(true);

  function ChangeOnClick(e) {
    src = e.target.getAttribute("src");
    console.log(src);
    setSrc(src);
  }

  const [alignment, setAlignment] = useState("web");

  const handleChange = (event, newAlignment) => {
    setAlignment(newAlignment);
  };

  return (
    <div>
      <Navbar home={true} />
      <div className="bg-white   sm:ml-5 py-6 sm:py-8 lg:py-12">
        <div className="mx-auto max-w-screen-xl px-4 md:px-8">
          <div className="grid gap-8  md:grid-cols-2">
            {/* <!-- images - start --> */}
            <div className="grid gap-4 lg:grid-cols-5 ">
              <div className="order-last flex gap-4 lg:order-none lg:flex-col">
                <div className="overflow-hidden rounded-lg bg-gray-100 ">
                  <img
                    src={ang1}
                    loading="lazy"
                    alt="Photo by Himanshu Dewangan"
                    onClick={ChangeOnClick}
                    className="h-full w-full object-cover object-center"
                  />
                </div>

                <div className="overflow-hidden rounded-lg bg-gray-100">
                  <img
                    src={ang2}
                    loading="lazy"
                    alt="Photo by Himanshu Dewangan"
                    onClick={ChangeOnClick}
                    className="h-full w-full object-cover object-center"
                  />
                </div>

                <div className="overflow-hidden rounded-lg bg-gray-100">
                  <img
                    src={ang3}
                    loading="lazy"
                    alt="Photo by Himanshu Dewangan"
                    onClick={ChangeOnClick}
                    className="h-full w-full object-cover object-center"
                  />
                </div>
              </div>

              <div className="relative overflow-hidden rounded-lg bg-gray-100 lg:col-span-4">
                <img
                  src={src}
                  loading="lazy"
                  alt="Photo by Himanshu Dewangan"
                  className="h-full w-full object-cover object-center"
                />

                <span className="absolute left-0 top-0 rounded-br-lg bg-red-500 px-3 py-1.5 text-sm uppercase tracking-wider text-white">
                  sale
                </span>

                <button
                  onClick={() => {
                    setShow4(!show4);
                    let stro = show4 ? "added to" : "removed from";
                    alert.success(`Fancy Outfit ${stro} watchlist!`);
                  }}
                  className="absolute right-4 top-4 inline-block rounded-lg border bg-white px-3.5 py-3 text-center text-sm font-semibold text-gray-500 outline-none ring-indigo-300 transition duration-100 hover:bg-gray-100 focus-visible:ring active:text-gray-700 md:text-base"
                >
                  {show4 ? (
                    <FavoriteBorderOutlinedIcon style={{ fontSize: "24px" }} />
                  ) : (
                    <FavoriteOutlinedIcon
                      style={{ color: "#FE5455", fontSize: "24px" }}
                    />
                  )}
                </button>
              </div>
            </div>
            {/* <!-- images - end -->

      <!-- content - start --> */}
            <div className="md:py-8">
              {/* <!-- name - start --> */}
              <div className="mb-2 md:mb-3">
                <span className="mb-0.5 inline-block text-gray-500">
                  Fancy Brand
                </span>
                <h2 className="text-2xl font-bold text-gray-800 lg:text-3xl">
                  Pullover with pattern
                </h2>
              </div>
              {/* <!-- name - end -->

        <!-- rating - start --> */}
              <div className="mb-6 flex items-center gap-3 md:mb-10">
                <div className="flex h-7 items-center gap-1 rounded-full bg-black px-2 text-white">
                  <span className="text-sm">4.2</span>

                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                  </svg>
                </div>

                <span className="text-sm text-gray-500 transition duration-100">
                  56 ratings
                </span>
              </div>
              {/* <!-- rating - end -->

        <!-- color - start --> */}
              <div className="mb-4 md:mb-6">
                <span className="mb-3 inline-block text-sm font-semibold text-gray-500 md:text-base">
                  Color
                </span>

                <div className="flex flex-wrap gap-2">
                  <span className="h-8 w-8 rounded-full border bg-gray-800 ring-2 ring-gray-800 ring-offset-1 transition duration-100"></span>
                  <button
                    type="button"
                    className="h-8 w-8 rounded-full border bg-gray-500 ring-2 ring-transparent ring-offset-1 transition duration-100 hover:ring-gray-200"
                  ></button>
                  <button
                    type="button"
                    className="h-8 w-8 rounded-full border bg-gray-200 ring-2 ring-transparent ring-offset-1 transition duration-100 hover:ring-gray-200"
                  ></button>
                  <button
                    type="button"
                    className="h-8 w-8 rounded-full border bg-white ring-2 ring-transparent ring-offset-1 transition duration-100 hover:ring-gray-200"
                  ></button>
                </div>
              </div>
              {/* <!-- color - end -->

        <!-- size - start --> */}
              <div className="mb-8 md:mb-10">
                <span className="mb-3 inline-block text-sm font-semibold text-gray-500 md:text-base">
                  Size
                </span>

                <div className="flex flex-wrap gap-3">
                  <ToggleButtonGroup
                    color="primary"
                    value={alignment}
                    exclusive
                    onChange={handleChange}
                    aria-label="Platform"
                  >
                    <ToggleButton style={{}} value="XS">
                      XS
                    </ToggleButton>
                    <ToggleButton value="S">S</ToggleButton>
                    <ToggleButton value="M">M</ToggleButton>
                    <ToggleButton value="L">L</ToggleButton>
                    <ToggleButton value="XL">XL</ToggleButton>
                  </ToggleButtonGroup>
                </div>

                {/* <div className="flex flex-wrap gap-3">
                  <button
                    type="button"
                    className="flex h-8 w-12 items-center justify-center rounded-md border bg-white text-center text-sm font-semibold text-gray-800 transition duration-100 hover:bg-gray-100 active:bg-gray-200"
                  >
                    XS
                  </button>
                  <button
                    type="button"
                    className="flex h-8 w-12 items-center justify-center rounded-md border bg-white text-center text-sm font-semibold text-gray-800 transition duration-100 hover:bg-gray-100 active:bg-gray-200"
                  >
                    S
                  </button>
                  <span className="flex h-8 w-12 cursor-default items-center justify-center rounded-md border border-black bg-black text-center text-sm font-semibold text-white">
                    M
                  </span>
                  <button
                    type="button"
                    className="flex h-8 w-12 items-center justify-center rounded-md border bg-white text-center text-sm font-semibold text-gray-800 transition duration-100 hover:bg-gray-100 active:bg-gray-200"
                  >
                    L
                  </button>
                  <span className="flex h-8 w-12 cursor-not-allowed items-center justify-center rounded-md border border-transparent bg-white text-center text-sm font-semibold text-gray-400">
                    XL
                  </span>
                </div>*/}
              </div>
              {/* <!-- size - end -->

        <!-- price - start --> */}
              <div className="mb-4">
                <div className="flex items-end gap-2">
                  <span className="text-xl font-bold text-gray-800 md:text-2xl">
                    $15.00
                  </span>
                </div>

                <span className="text-sm text-gray-500">
                  incl. VAT plus shipping
                </span>
              </div>
              {/* <!-- price - end -->

        <!-- shipping notice - start --> */}
              <div className="mb-6 flex items-center gap-2 text-gray-500">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-6 w-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path d="M9 17a2 2 0 11-4 0 2 2 0 014 0zM19 17a2 2 0 11-4 0 2 2 0 014 0z" />
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M13 16V6a1 1 0 00-1-1H4a1 1 0 00-1 1v10a1 1 0 001 1h1m8-1a1 1 0 01-1 1H9m4-1V8a1 1 0 011-1h2.586a1 1 0 01.707.293l3.414 3.414a1 1 0 01.293.707V16a1 1 0 01-1 1h-1m-6-1a1 1 0 001 1h1M5 17a2 2 0 104 0m-4 0a2 2 0 114 0m6 0a2 2 0 104 0m-4 0a2 2 0 114 0"
                  />
                </svg>

                <span className="text-sm">2-4 day shipping</span>
              </div>
              {/* <!-- shipping notice - end -->

        <!-- buttons - start --> */}
              <div className="flex gap-2.5">
                <button
                  onClick={() => {
                    alert.success("Fancy Outfit added to cart!");
                  }}
                  className="inline-block flex-1 rounded-lg bg-black     px-8 py-3 text-center text-sm font-semibold text-white outline-none ring-indigo-300 transition duration-100 hover:bg-white hover:text-black hover:border hover:border-black focus-visible:ring active:bg-black sm:flex-none md:text-base"
                >
                  Add to cart
                </button>

                <a
                  href="/cart"
                  className="inline-block rounded-lg bg-gray-200 px-8 py-3 text-center text-sm font-semibold text-gray-500 outline-none ring-indigo-300 transition duration-100 hover:bg-gray-300 focus-visible:ring active:text-gray-700 md:text-base"
                >
                  Buy now
                </a>
              </div>
              {/* <!-- buttons - end --> */}
            </div>
            {/* <!-- content - end --> */}
          </div>
        </div>
      </div>
      <div className=" mx-10">
        <Tabswitch />
      </div>

      <div>
        <div className="border-y-4 border-gray-300 my-10 ">
          <h1 className=" text-3xl font-medium text-slate-900 py-5 px-10">
            YOU MAY ALSO LIKE
          </h1>
        </div>
        <Card />
        <div className="border-y-4 border-gray-300 my-10 ">
          <h1 className=" text-3xl font-medium text-slate-900 py-5 px-10">
            RELATED PRODUCTS
          </h1>
        </div>
        <Card />
        <Card />
        <Card />
      </div>
      <Footer />
    </div>
  );
}
