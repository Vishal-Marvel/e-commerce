// eslint-disable-next-line no-unused-vars
import React from "react";
import video from "../assets/app.mp4";
import Footer from "../Components/Footer";
import Navbar from "../Components/Navbar";
import Card from "../Components/Card";
import { Player } from "video-react";

export default function Land(props) {
  return (
    <div>
      <Navbar home={props.home} />
      <div className=" mt-0 h-80">
        <video src={video} autoPlay muted loop className="video-bg  "></video>
      </div>
      {/* <!-- hero - end -->

<!-- product-grid - start --> */}
      <div className="bg-white py-6 sm:py-8 lg:py-12">
        <div className="mx-auto max-w-screen-2xl px-4 md:px-8">
          <div className="bg-white py-6 sm:py-8 lg:py-12">
            <div className="mx-auto max-w-screen-2xl px-4 md:px-8">
              <h2 className="mb-8 text-center text-2xl font-bold text-white md:mb-12 lg:text-3xl">
                Categories
              </h2>

              <div className="grid gap-4 sm:grid-cols-2 md:gap-6 lg:grid-cols-3 xl:grid-cols-4">
                {/* <!-- product - start --> */}
                <div>
                  <a
                    href="/products"
                    className="group relative flex h-96 items-end overflow-hidden rounded-lg bg-gray-100 p-4 shadow-lg"
                  >
                    <img
                      src="https://images.unsplash.com/photo-1552374196-1ab2a1c593e8?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                      loading="lazy"
                      alt="Photo by Austin Wade"
                      className="absolute inset-0 h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                    />

                    <div className="relative flex w-full flex-col rounded-lg bg-white p-4 text-center">
                      <span className="text-lg font-bold text-gray-800 lg:text-xl">
                        Men
                      </span>
                    </div>
                  </a>
                </div>
                {/* <!-- product - end -->

      <!-- product - start --> */}
                <div>
                  <a
                    href="/products"
                    className="group relative flex h-96 items-end overflow-hidden rounded-lg bg-gray-100 p-4 shadow-lg"
                  >
                    <img
                      src="https://images.unsplash.com/photo-1603344797033-f0f4f587ab60?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                      loading="lazy"
                      alt="Photo by engin akyurt"
                      className="absolute inset-0 h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                    />

                    <div className="relative flex w-full flex-col rounded-lg bg-white p-4 text-center">
                      <span className="text-lg font-bold text-gray-800 lg:text-xl">
                        Women
                      </span>
                    </div>
                  </a>
                </div>
                {/* <!-- product - end -->

      <!-- product - start --> */}
                <div>
                  <a
                    href="/products"
                    className="group relative flex h-96 items-end overflow-hidden rounded-lg bg-gray-100 p-4 shadow-lg"
                  >
                    <img
                      src="https://imgmedia.lbb.in/media/2023/03/64269ee4c70b643ca53755fa_1680252644297.jpg"
                      loading="lazy"
                      alt="Photo by Austin Wade"
                      className="absolute inset-0 h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                    />

                    <div className="relative flex w-full flex-col rounded-lg bg-white p-4 text-center">
                      <span className="text-lg font-bold text-gray-800 lg:text-xl">
                        Home Decors
                      </span>
                    </div>
                  </a>
                </div>
                {/* <!-- product - end -->

      <!-- product - start --> */}
                <div>
                  <a
                    href="/products"
                    className="group relative flex h-96 items-end overflow-hidden rounded-lg bg-gray-100 p-4 shadow-lg"
                  >
                    <img
                      src="https://cdn.shopify.com/s/files/1/0584/4035/1905/products/rome_800x.jpg?v=1637422522"
                      loading="lazy"
                      alt="Photo by Austin Wade"
                      className="absolute inset-0 h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                    />

                    <div className="relative flex w-full flex-col rounded-lg bg-white p-4 text-center">
                      <span className="text-lg font-bold text-gray-800 lg:text-xl">
                        Leather Products
                      </span>
                    </div>
                  </a>
                </div>
                {/* <!-- product - end --> */}
              </div>
            </div>
          </div>
          <div className="bg-white py-6 sm:py-8 lg:py-12">
            <div className="mx-auto max-w-screen-2xl px-4 md:px-8">
              <div className="flex flex-col overflow-hidden rounded-lg bg-gray-900 sm:flex-row md:h-80">
                {/* <!-- content - start --> */}
                <div className="flex w-full flex-col p-4 sm:w-1/2 sm:p-8 lg:w-2/5">
                  <h2 className="mb-4 text-xl font-bold text-white md:text-2xl lg:text-4xl">
                    Summer Sale
                    <br />
                    Up to 70% off.
                  </h2>

                  <p className="mb-8 max-w-md text-gray-400">
                    This is a section of some simple filler text, also known as
                    placeholder text. It shares some characteristics of a real
                    written text.
                  </p>

                  <div className="mt-auto">
                    <a
                      href="/products"
                      className="inline-block rounded-lg bg-white px-8 py-3  text-center text-sm font-semibold text-gray-800 outline-none ring-indigo-300 transition duration-100 hover:bg-gray-100 focus-visible:ring active:bg-gray-200 md:text-base"
                    >
                      View now
                    </a>
                  </div>
                </div>
                {/* <!-- content - end -->

      <!-- image - start --> */}
                <div className="order-first h-48 w-full bg-gray-700 sm:order-none sm:h-auto sm:w-1/2 lg:w-3/5">
                  <img
                    src="https://images.unsplash.com/photo-1505846951821-e25bacf2eccd?auto=format&q=75&fit=crop&crop=top&w=1000&h=500"
                    loading="lazy"
                    alt="Photo by Dom Hill"
                    className="h-full w-full object-cover object-center"
                  />
                </div>
                {/* <!-- image - end --> */}
              </div>
            </div>
          </div>
          {/* <!-- text - start --> */}
          <div className="mb-10 md:mb-16">
            <h2 className="mb-4 text-center text-2xl font-bold text-gray-800 md:mb-6 lg:text-3xl">
              Top Products
            </h2>

            <p className="mx-auto max-w-screen-md text-center text-gray-500 md:text-lg">
              This is a section of some simple filler text, also known as
              placeholder text. It shares some characteristics of a real written
              text but is random or otherwise generated.
            </p>
          </div>
          {/* <!-- text - end --> */}

          <Card />
          <Card />
        </div>
      </div>
      {/* <!-- product-grid - end -->

<!-- call to action - start --> */}

      {/* <!-- call to action - end -->

<!-- collections - start --> */}

      {/* <!-- collections - end -->

<!-- footer - start --> */}
      <Footer />
    </div>
  );
}
