import React, { useState } from "react";
import { Box, Tab, Tabs, Typography } from "@mui/material";
import Rating from "@mui/material/Rating";
import StarIcon from "@mui/icons-material/Star";

const labels = {
  0.5: "Useless",
  1: "Useless+",
  1.5: "Poor",
  2: "Poor+",
  2.5: "Ok",
  3: "Ok+",
  3.5: "Good",
  4: "Good+",
  4.5: "Excellent",
  5: "Excellent+",
};

function getLabelText(value) {
  return `${value} Star${value !== 1 ? "s" : ""}, ${labels[value]}`;
}

function Tabswitch() {
  const [currentTabIndex, setCurrentTabIndex] = useState(0);
  const [value, setValue] = React.useState(0);
  const [hover, setHover] = React.useState(-1);
  const [open, setOpen] = useState(false);

  const handleTabChange = (e, tabIndex) => {
    console.log(tabIndex);
    setCurrentTabIndex(tabIndex);
  };
  return (
    <React.Fragment>
      <Tabs value={currentTabIndex} onChange={handleTabChange}>
        <Tab label="Description" />
        <Tab label="Review" />
      </Tabs>

      {/* TAB 1 Contents */}
      {currentTabIndex === 0 && (
        <Box sx={{ p: 3 }}>
          <Typography variant="h5">Description</Typography>
          <Typography variant="p">
            <p classNameName="tbp">
              Introducing the stunning "White Dotted Frock" - a delightful and
              enchanting addition to your wardrobe. This exquisite frock is
              designed to make you feel like the epitome of elegance and grace.
              Crafted with meticulous attention to detail, it seamlessly blends
              timeless style with contemporary fashion. The White Dotted Frock
              is fashioned from high-quality, breathable fabric that ensures
              all-day comfort. Its pristine white hue adds a touch of purity and
              sophistication, while the delicate polka dot pattern sprinkles a
              playful charm that will surely turn heads wherever you go. The
              frock features a flattering silhouette that accentuates your
              feminine curves, making you feel both confident and beautiful.{" "}
            </p>

            <p classNameName="tbp">
              Its graceful neckline and perfectly tailored bodice enhance your
              natural grace, while the flowing skirt adds a whimsical flair to
              your overall look. With its versatile design, the White Dotted
              Frock is suitable for various occasions, be it a formal event, a
              casual outing, or a special celebration. Dress it up with a
              statement necklace and heels for a glamorous evening ensemble, or
              pair it with flats and a wide-brimmed hat for a chic daytime look.
              To ensure the utmost convenience, this frock is equipped with a
              hidden zipper closure at the back, allowing for easy on and off.{" "}
            </p>

            <p classNameName="tbp">
              The fabric is also low maintenance, requiring minimal effort to
              keep it looking flawless. Available in a range of sizes, the White
              Dotted Frock caters to different body types, allowing every woman
              to indulge in its enchanting allure. Embrace the timeless beauty
              of this garment and experience the joy of wearing a truly
              remarkable piece. Elevate your style and captivate hearts with the
              White Dotted Frock - a timeless treasure that will never go out of
              fashion.
            </p>
          </Typography>
        </Box>
      )}

      {/* TAB 2 Contents */}
      {currentTabIndex === 1 && (
        <div className="bg-white py-6 sm:py-8 lg:py-12">
          <div className="mx-auto max-w-screen-xl px-4 md:px-8">
            <div className="grid gap-8 sm:grid-cols-2 lg:grid-cols-3 lg:gap-12">
              {/* <!-- overview - start --> */}
              <div>
                <div className="rounded-lg border p-4">
                  <h2 className="mb-3 text-lg font-bold text-gray-800 lg:text-xl">
                    Customer Reviews
                  </h2>

                  <div className="mb-0.5 flex items-center gap-2">
                    {/* <!-- stars - start --> */}
                    <div className="-ml-1 flex gap-0.5">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-6 w-6 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-6 w-6 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-6 w-6 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-6 w-6 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-6 w-6 text-gray-300"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>
                    </div>
                    {/* <!-- stars - end --> */}

                    <span className="text-sm font-semibold">4/5</span>
                  </div>

                  <span className="block text-sm text-gray-500">
                    Bases on 27 reviews
                  </span>

                  <div className="my-5 flex flex-col gap-2 border-t border-b py-5">
                    {/* <!-- star - start --> */}
                    <div className="flex items-center gap-3">
                      <span className="w-10 whitespace-nowrap text-right text-sm text-gray-600">
                        5 Star
                      </span>

                      <div className="flex h-4 flex-1 overflow-hidden rounded bg-gray-200">
                        <span className="h-full w-3/4 rounded bg-black"></span>
                      </div>
                    </div>
                    {/* <!-- star - end --> */}

                    {/* <!-- star - start --> */}
                    <div className="flex items-center gap-3">
                      <span className="w-10 whitespace-nowrap text-right text-sm text-gray-600">
                        4 Star
                      </span>

                      <div className="flex h-4 flex-1 overflow-hidden rounded bg-gray-200">
                        <span className="h-full w-1/2 rounded bg-black"></span>
                      </div>
                    </div>
                    {/* <!-- star - end --> */}

                    {/* <!-- star - start --> */}
                    <div className="flex items-center gap-3">
                      <span className="w-10 whitespace-nowrap text-right text-sm text-gray-600">
                        3 Star
                      </span>

                      <div className="flex h-4 flex-1 overflow-hidden rounded bg-gray-200">
                        <span className="h-full w-1/6 rounded bg-black"></span>
                      </div>
                    </div>
                    {/* <!-- star - end --> */}

                    {/* <!-- star - start --> */}
                    <div className="flex items-center gap-3">
                      <span className="w-10 whitespace-nowrap text-right text-sm text-gray-600">
                        2 Star
                      </span>

                      <div className="flex h-4 flex-1 overflow-hidden rounded bg-gray-200">
                        <span className="h-full w-1/4 rounded bg-black"></span>
                      </div>
                    </div>
                    {/* <!-- star - end --> */}

                    {/* <!-- star - start --> */}
                    <div className="flex items-center gap-3">
                      <span className="w-10 whitespace-nowrap text-right text-sm text-gray-600">
                        1 Star
                      </span>

                      <div className="flex h-4 flex-1 overflow-hidden rounded bg-gray-200">
                        <span className="h-full w-1/12 rounded bg-black"></span>
                      </div>
                    </div>
                    {/* <!-- star - end --> */}
                  </div>

                  <button
                    className="block rounded-lg border bg-white px-4 py-2 text-center text-sm font-semibold text-gray-500 outline-none ring-indigo-300 transition duration-100 hover:bg-gray-100 focus-visible:ring active:bg-gray-200 md:px-8 md:py-3 md:text-base"
                    onClick={() => setOpen(true)}
                  >
                    Write a review
                  </button>
                </div>

                {open && (
                  <div className="mt-2 h-48 py-2 border-b-slate-200 border shadow-[0_1px_1px_1px_rgba(0,0,0,0.14)] border-solid border-[rgba(0,0,0,0.04)]">
                    <div className="flex items center">
                      <h1 className="pl-6 font-semibold">Your Rating</h1>
                      <Rating
                        name="hover-feedback"
                        className="ml-2"
                        value={value}
                        precision={0.5}
                        getLabelText={getLabelText}
                        onChange={(event, newValue) => {
                          setValue(newValue);
                        }}
                        onChangeActive={(event, newHover) => {
                          setHover(newHover);
                        }}
                        style={{ color: "black" }}
                      />
                      {value !== null && (
                        <Box sx={{ ml: 2 }}>
                          {labels[hover !== -1 ? hover : value]}
                        </Box>
                      )}
                    </div>
                    <div className="flex justify-center">
                      <textarea
                        type="text"
                        id="landmark"
                        name="landmark"
                        placeholder="Write your review."
                        className="mt-2 h-24 w-80 bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                      />
                    </div>
                    <div className="flex justify-end mr-5 mt-2">
                      <button className="block rounded-lg border border-solid border-gray-400 bg-white px-2 py-1 text-center text-sm font-semibold text-gray-500 outline-none ring-indigo-300 transition duration-100 hover:bg-gray-100 focus-visible:ring active:bg-gray-200 md:px-4 md:py-1 md:text-base">
                        Submmit
                      </button>
                    </div>
                  </div>
                )}
              </div>

              {/* <!-- overview - end --> */}

              {/* <!-- reviews - start --> */}
              <div className="lg:col-span-2">
                <div className="border-b pb-4 md:pb-6">
                  <h2 className="text-lg font-bold text-gray-800 lg:text-xl">
                    Top Reviews
                  </h2>
                </div>

                <div className="divide-y">
                  {/* <!-- review - start --> */}
                  <div className="flex flex-col gap-3 py-4 md:py-8">
                    <div>
                      <span className="block text-sm font-bold">
                        John McCulling
                      </span>
                      <span className="block text-sm text-gray-500">
                        August 28, 2021
                      </span>
                    </div>

                    {/* <!-- stars - start --> */}
                    <div className="-ml-1 flex gap-0.5">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>
                    </div>
                    {/* <!-- stars - end --> */}

                    <p className="text-gray-600">
                      This is a section of some simple filler text, also known
                      as placeholder text. It shares some characteristics of a
                      real written text but is random or otherwise generated. It
                      may be used to display a sample of fonts or generate text
                      for testing.
                    </p>
                  </div>
                  {/* <!-- review - end --> */}

                  {/* <!-- review - start --> */}
                  <div className="flex flex-col gap-3 py-4 md:py-8">
                    <div>
                      <span className="block text-sm font-bold">Kate Berg</span>
                      <span className="block text-sm text-gray-500">
                        July 21, 2021
                      </span>
                    </div>

                    {/* <!-- stars - start --> */}
                    <div className="-ml-1 flex gap-0.5">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>
                    </div>
                    {/* <!-- stars - end --> */}

                    <p className="text-gray-600">
                      This is a section of some simple filler text, also known
                      as placeholder text. It shares some characteristics of a
                      real written text but is random or otherwise generated. It
                      may be used to display a sample of fonts or generate text
                      for testing.
                    </p>
                  </div>
                  {/* <!-- review - end --> */}

                  {/* <!-- review - start --> */}
                  <div className="flex flex-col gap-3 py-4 md:py-8">
                    <div>
                      <span className="block text-sm font-bold">
                        Greg Jackson
                      </span>
                      <span className="block text-sm text-gray-500">
                        March 16, 2021
                      </span>
                    </div>

                    {/* <!-- stars - start --> */}
                    <div className="-ml-1 flex gap-0.5">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-black"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-gray-300"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>

                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-5 w-5 text-gray-300"
                        viewBox="0 0 20 20"
                        fill="currentColor"
                      >
                        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                      </svg>
                    </div>
                    {/* <!-- stars - end --> */}

                    <p className="text-gray-600">
                      This is a section of some simple filler text, also known
                      as placeholder text. It shares some characteristics of a
                      real written text but is random or otherwise generated. It
                      may be used to display a sample of fonts or generate text
                      for testing.
                    </p>
                  </div>
                  {/* <!-- review - end --> */}
                </div>

                <div className="border-t pt-6">
                  <a
                    href="#"
                    className="flex items-center gap-0.5 font-semibold text-indigo-400 transition duration-100 hover:text-indigo-500 active:text-indigo-600"
                  >
                    Read all reviews
                  </a>
                </div>
              </div>
              {/* <!-- reviews - end --> */}
            </div>
          </div>
        </div>
      )}

      {/* TAB 3 Contents */}
      {currentTabIndex === 2 && (
        <Box sx={{ p: 3 }}>
          <Typography variant="h5">Tab 3 Content</Typography>
          <Typography variant="p">
            It is a long established fact that a reader will be distracted by
            the readable content of a page when looking at its layout. The point
            of using Lorem Ipsum is that it has a more-or-less normal
            distribution of letters, as op
          </Typography>
        </Box>
      )}

      {/* TAB 4 Contents */}
      {currentTabIndex === 3 && (
        <Box sx={{ p: 3 }}>
          <Typography variant="h5">Tab 4 Content</Typography>
          <Typography variant="p">
            The standard chunk of Lorem Ipsum used since the 1500s is reproduced
            below for those interested. Sections 1.10.32 and 1.10.33 from "de
            Finibus Bonorum et Malorum" by Cicero are also reproduced in their
            exact original form,
          </Typography>
        </Box>
      )}
    </React.Fragment>
  );
}

export default Tabswitch;
