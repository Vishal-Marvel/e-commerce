import { Fragment, useState } from "react";
import { Dialog, Disclosure, Menu, Transition } from "@headlessui/react";
import { XIcon } from "@heroicons/react/outline";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import Rating from "@mui/material/Rating";
import { useAlert } from "react-alert";
import Slider from "@material-ui/core/Slider";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import Footer from "../Components/Footer";
import {
  ChevronDownIcon,
  FilterIcon,
  MinusSmIcon,
  PlusSmIcon,
} from "@heroicons/react/solid";
import Navbar from "../Components/Navbar";

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
    color: "#000",
    height: 8,
  },
  thumb: {
    height: 24,
    width: 24,
    backgroundColor: "#fff",
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

const sortOptions = [
  { name: "Most Popular", href: "#", current: true },
  { name: "Best Rating", href: "#", current: false },
  { name: "Newest", href: "#", current: false },
  { name: "Price: Low to High", href: "#", current: false },
  { name: "Price: High to Low", href: "#", current: false },
];
const subCategories = [
  { name: "Men Fashion", href: "#" },
  { name: "Women Fashion", href: "#" },
  { name: "Home Decors", href: "#" },
  { name: "Electronics", href: "#" },
];
const filters = [
  {
    id: "color",
    name: "Color",
    options: [
      { value: "white", label: "White", checked: false },
      { value: "beige", label: "Beige", checked: false },
      { value: "blue", label: "Blue", checked: true },
      { value: "brown", label: "Brown", checked: false },
      { value: "green", label: "Green", checked: false },
      { value: "purple", label: "Purple", checked: false },
    ],
  },
  {
    id: "category",
    name: "Category",
    options: [
      { value: "new-arrivals", label: "New Arrivals", checked: false },
      { value: "sale", label: "Sale", checked: false },
      { value: "travel", label: "Travel", checked: true },
      { value: "organization", label: "Organization", checked: false },
      { value: "accessories", label: "Accessories", checked: false },
    ],
  },
  {
    id: "size",
    name: "Size",
    options: [
      { value: "S", label: "S", checked: false },
      { value: "M", label: "M", checked: false },
      { value: "L", label: "L", checked: false },
      { value: "XL", label: "XL", checked: false },
      { value: "2XL", label: "2XL", checked: false },
      { value: "3XL", label: "3XL", checked: true },
    ],
  },
];

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Product() {
  const [mobileFiltersOpen, setMobileFiltersOpen] = useState(false);

  const [value, setValue] = useState([0, 5000]);

  const rangeSelector = (event, newValue) => {
    setValue(newValue);
    console.log(newValue);
  };
  const classes = useStyles();

  return (
    <div className="bg-white">
      <Navbar home={true} />
      <div>
        {/* Mobile filter dialog */}
        <Transition.Root show={mobileFiltersOpen} as={Fragment}>
          <Dialog
            as="div"
            className="fixed inset-0 flex z-40 lg:hidden"
            onClose={setMobileFiltersOpen}
          >
            <Transition.Child
              as={Fragment}
              enter="transition-opacity ease-linear duration-300"
              enterFrom="opacity-0"
              enterTo="opacity-100"
              leave="transition-opacity ease-linear duration-300"
              leaveFrom="opacity-100"
              leaveTo="opacity-0"
            >
              <Dialog.Overlay className="fixed inset-0 bg-black bg-opacity-25" />
            </Transition.Child>

            <Transition.Child
              as={Fragment}
              enter="transition ease-in-out duration-300 transform"
              enterFrom="translate-x-full"
              enterTo="translate-x-0"
              leave="transition ease-in-out duration-300 transform"
              leaveFrom="translate-x-0"
              leaveTo="translate-x-full"
            >
              <div className="ml-auto relative max-w-xs w-full h-full bg-white shadow-xl py-4 pb-12 flex flex-col overflow-y-auto">
                <div className="px-4 flex items-center justify-between">
                  <h2 className="text-lg font-medium text-gray-900">Filters</h2>
                  <button
                    type="button"
                    className="-mr-2 w-10 h-10 bg-white p-2 rounded-md flex items-center justify-center text-gray-400"
                    onClick={() => setMobileFiltersOpen(false)}
                  >
                    <span className="sr-only">Close menu</span>
                    <XIcon className="h-6 w-6" aria-hidden="true" />
                  </button>
                </div>

                {/* Filters */}
                <form className="mt-4 border-t border-gray-200">
                  <h3 className="sr-only">Categories</h3>
                  <ul
                    role="list"
                    className="font-medium text-gray-900 px-2 py-3"
                  >
                    {subCategories.map((category) => (
                      <li key={category.name}>
                        <a href={category.href} className="block px-2 py-3">
                          {category.name}
                        </a>
                      </li>
                    ))}
                  </ul>

                  {filters.map((section) => (
                    <Disclosure
                      as="div"
                      key={section.id}
                      className="border-t border-gray-200 px-4 py-6"
                    >
                      {({ open }) => (
                        <>
                          <h3 className="-mx-2 -my-3 flow-root">
                            <Disclosure.Button className="px-2 py-3 bg-white w-full flex items-center justify-between text-gray-400 hover:text-gray-500">
                              <span className="font-medium text-gray-900">
                                {section.name}
                              </span>
                              <span className="ml-6 flex items-center">
                                {open ? (
                                  <MinusSmIcon
                                    className="h-5 w-5"
                                    aria-hidden="true"
                                  />
                                ) : (
                                  <PlusSmIcon
                                    className="h-5 w-5"
                                    aria-hidden="true"
                                  />
                                )}
                              </span>
                            </Disclosure.Button>
                          </h3>
                          <Disclosure.Panel className="pt-6">
                            <div className="space-y-6">
                              {section.options.map((option, optionIdx) => (
                                <div
                                  key={option.value}
                                  className="flex items-center"
                                >
                                  <input
                                    id={`filter-mobile-${section.id}-${optionIdx}`}
                                    name={`${section.id}[]`}
                                    defaultValue={option.value}
                                    type="checkbox"
                                    defaultChecked={option.checked}
                                    className="h-4 w-4 border-gray-300 rounded text-black focus:ring-black"
                                  />
                                  <label
                                    htmlFor={`filter-mobile-${section.id}-${optionIdx}`}
                                    className="ml-3 min-w-0 flex-1 text-gray-500"
                                  >
                                    {option.label}
                                  </label>
                                </div>
                              ))}
                            </div>
                          </Disclosure.Panel>
                        </>
                      )}
                    </Disclosure>
                  ))}
                  <Disclosure
                    as="div"
                    key={4}
                    className="border-t border-gray-200 px-4 py-6"
                  >
                    {({ open }) => (
                      <>
                        <Disclosure.Button className="py-3 bg-white w-full flex items-center justify-between text-sm text-gray-400 hover:text-gray-500">
                          <span className="font-medium text-gray-900">
                            {"Price"}
                          </span>
                          <span className="ml-6 flex items-center">
                            {open ? (
                              <MinusSmIcon
                                className="h-5 w-5"
                                aria-hidden="true"
                              />
                            ) : (
                              <PlusSmIcon
                                className="h-5 w-5"
                                aria-hidden="true"
                              />
                            )}
                          </span>
                        </Disclosure.Button>
                        <Disclosure.Panel className="pt-6">
                          <div className="flex justify-center">
                            <div className="">
                              <div
                                className={classes.root}
                                style={{ width: "220px" }}
                              >
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
                                  className="outline-none border border-gray-500 border-solid w-24 px-3 py-3 text-black text-right font-medium text-lg"
                                />
                                <h1 className="text-center my-auto mx-4 text-lg">
                                  to
                                </h1>
                                <input
                                  type="text"
                                  value={`$ ${value[1]}`}
                                  className="outline-none border border-gray-500 border-solid w-24 px-3 py-3 text-black text-center font-medium text-lg"
                                />
                              </div>
                            </div>
                          </div>
                        </Disclosure.Panel>
                      </>
                    )}
                  </Disclosure>
                </form>
              </div>
            </Transition.Child>
          </Dialog>
        </Transition.Root>

        <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="relative z-10 flex items-baseline justify-between pt-24 pb-6 border-b border-gray-200">
            <h1 className="text-4xl font-extrabold tracking-tight text-gray-900">
              Our Products
            </h1>

            <div className="flex items-center">
              <Menu as="div" className="relative inline-block text-left">
                <div>
                  <Menu.Button className="group inline-flex justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                    Sort
                    <ChevronDownIcon
                      className="flex-shrink-0 -mr-1 ml-1 h-5 w-5 text-gray-400 group-hover:text-gray-500"
                      aria-hidden="true"
                    />
                  </Menu.Button>
                </div>

                <Transition
                  as={Fragment}
                  enter="transition ease-out duration-100"
                  enterFrom="transform opacity-0 scale-95"
                  enterTo="transform opacity-100 scale-100"
                  leave="transition ease-in duration-75"
                  leaveFrom="transform opacity-100 scale-100"
                  leaveTo="transform opacity-0 scale-95"
                >
                  <Menu.Items className="origin-top-right absolute right-0 mt-2 w-40 rounded-md shadow-2xl bg-white ring-1 ring-black ring-opacity-5 focus:outline-none">
                    <div className="py-1">
                      {sortOptions.map((option) => (
                        <Menu.Item key={option.name}>
                          {({ active }) => (
                            <a
                              href={option.href}
                              className={classNames(
                                option.current
                                  ? "font-medium text-gray-900"
                                  : "text-gray-500",
                                active ? "bg-gray-100" : "",
                                "block px-4 py-2 text-sm"
                              )}
                            >
                              {option.name}
                            </a>
                          )}
                        </Menu.Item>
                      ))}
                    </div>
                  </Menu.Items>
                </Transition>
              </Menu>

              <button
                type="button"
                className="p-2 -m-2 ml-5 sm:ml-7 text-gray-400 hover:text-gray-500"
              >
                <span className="sr-only">View grid</span>
                {/* <ViewGridIcon className="w-5 h-5" aria-hidden="true" /> */}
              </button>
              <button
                type="button"
                className="p-2 -m-2 ml-4 sm:ml-6 text-gray-400 hover:text-gray-500 lg:hidden"
                onClick={() => setMobileFiltersOpen(true)}
              >
                <span className="sr-only">Filters</span>
                <FilterIcon className="w-5 h-5" aria-hidden="true" />
              </button>
            </div>
          </div>

          <section aria-labelledby="products-heading" className="pt-6 pb-24">
            <h2 id="products-heading" className="sr-only">
              Products
            </h2>

            <div className=" grid grid-cols-1 lg:grid-cols-4 gap-y-2">
              {/* Filters */}
              <form className="hidden lg:block">
                <h3 className="sr-only">Categories</h3>
                <ul
                  role="list"
                  className="text-sm font-medium text-gray-900 space-y-4 pb-6 border-b border-gray-200"
                >
                  {subCategories.map((category) => (
                    <li key={category.name}>
                      <a href={category.href}>{category.name}</a>
                    </li>
                  ))}
                </ul>

                {filters.map((section) => (
                  <Disclosure
                    as="div"
                    key={section.id}
                    className="border-b border-gray-200 py-6"
                  >
                    {({ open }) => (
                      <>
                        <h3 className="-my-3 flow-root">
                          <Disclosure.Button className="py-3 bg-white w-full flex items-center justify-between text-sm text-gray-400 hover:text-gray-500">
                            <span className="font-medium text-gray-900">
                              {section.name}
                            </span>
                            <span className="ml-6 flex items-center">
                              {open ? (
                                <MinusSmIcon
                                  className="h-5 w-5"
                                  aria-hidden="true"
                                />
                              ) : (
                                <PlusSmIcon
                                  className="h-5 w-5"
                                  aria-hidden="true"
                                />
                              )}
                            </span>
                          </Disclosure.Button>
                        </h3>
                        <Disclosure.Panel className="pt-6">
                          <div className="space-y-4">
                            {section.options.map((option, optionIdx) => (
                              <div
                                key={option.value}
                                className="flex items-center"
                              >
                                <input
                                  id={`filter-${section.id}-${optionIdx}`}
                                  name={`${section.id}[]`}
                                  defaultValue={option.value}
                                  type="checkbox"
                                  defaultChecked={option.checked}
                                  className="h-4 w-4 border-gray-300 rounded text-black focus:ring-black"
                                />
                                <label
                                  htmlFor={`filter-${section.id}-${optionIdx}`}
                                  className="ml-3 text-sm text-gray-600"
                                >
                                  {option.label}
                                </label>
                              </div>
                            ))}
                          </div>
                        </Disclosure.Panel>
                      </>
                    )}
                  </Disclosure>
                ))}
                <Disclosure
                  as="div"
                  key={4}
                  className="border-b border-gray-200 py-6"
                >
                  {({ open }) => (
                    <>
                      <Disclosure.Button className="py-3 bg-white w-full flex items-center justify-between text-sm text-gray-400 hover:text-gray-500">
                        <span className="font-medium text-gray-900">
                          {"Price"}
                        </span>
                        <span className="ml-6 flex items-center">
                          {open ? (
                            <MinusSmIcon
                              className="h-5 w-5"
                              aria-hidden="true"
                            />
                          ) : (
                            <PlusSmIcon
                              className="h-5 w-5"
                              aria-hidden="true"
                            />
                          )}
                        </span>
                      </Disclosure.Button>
                      <Disclosure.Panel className="pt-6">
                        <div className="flex justify-center">
                          <div className="">
                            <div
                              className={classes.root}
                              style={{ width: "220px" }}
                            >
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
                                className="outline-none border border-gray-500 border-solid w-24 px-3 py-3 text-black text-right font-medium text-lg"
                              />
                              <h1 className="text-center my-auto mx-4 text-lg">
                                to
                              </h1>
                              <input
                                type="text"
                                value={`$ ${value[1]}`}
                                className="outline-none border border-gray-500 border-solid w-24 px-3 py-3 text-black text-center font-medium text-lg"
                              />
                            </div>
                          </div>
                        </div>
                      </Disclosure.Panel>
                    </>
                  )}
                </Disclosure>
                <div className=" justify-center mt-10 flex my-0">
                  <button className="text-lg rounded-md font-semibold border-solid  text-black py-2 px-4 border-zinc-900 hover:bg-black hover:text-white border">
                    Apply Filter
                  </button>
                </div>
              </form>
              {/* Product grid */}{" "}
              <div className="lg:col-span-3 w-full">
                {/* Replace with your content */}
                <Cards />
                <Cards />
                <Cards />
                {/* <div className="border-4 border-dashed border-gray-200 rounded-lg h-96 lg:h-full" /> */}
                {/* /End replace */}
              </div>
            </div>
          </section>
        </main>
      </div>
      <Footer />
    </div>
  );
}

function Cards(props) {
  const alert = useAlert();

  const [value, setValue] = useState(4.5);

  const [show1, setShow1] = useState(true);
  const [show2, setShow2] = useState(true);
  const [show4, setShow4] = useState(true);

  return (
    <div>
      <div className="bg-white py-6 sm:py-8 lg:py-12">
        <div className="mx-auto max-w-screen-2xl px-4 md:px-8">
          <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-2 xl:grid-cols-3">
            {/* <!-- product - start --> */}
            <div>
              <a
                href="/detials"
                className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
              >
                <img
                  src="https://images.unsplash.com/photo-1552374196-1ab2a1c593e8?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                  loading="lazy"
                  alt="Photo by Austin Wade"
                  className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                />
              </a>

              <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
                <div className="flex flex-col">
                  <a
                    href="/detials"
                    className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
                  >
                    Fancy Outfit
                  </a>

                  <div className="mt-1 flex items-center">
                    <Rating
                      name="read-only"
                      value={value}
                      readOnly
                      size="small"
                      style={{ color: "black" }}
                      precision={0.5}
                    />
                  </div>
                </div>

                <div className="flex flex-col items-end mt-1">
                  <span className="font-bold text-gray-600 lg:text-lg">
                    $19.99
                  </span>
                  <div className="flex items-center mt-1">
                    <button
                      onClick={() => {
                        setShow1(!show1);
                        let stro = show1 ? "added to" : "removed from";
                        alert.success(`Fancy Outfit ${stro} watchlist!`);
                      }}
                      className="mb-0.5"
                    >
                      {show1 ? (
                        <FavoriteBorderOutlinedIcon
                          style={{ fontSize: "24px" }}
                        />
                      ) : (
                        <FavoriteOutlinedIcon
                          style={{ color: "#FE5455", fontSize: "24px" }}
                        />
                      )}
                    </button>
                    <button
                      className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
                      onClick={() => {
                        alert.success("Fancy Outfit added to cart!");
                      }}
                    >
                      Add To Cart{" "}
                    </button>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- product - end --> */}

            {/* <!-- product - start --> */}
            <div>
              <a
                href="/detials"
                className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
              >
                <img
                  src="https://images.unsplash.com/photo-1523359346063-d879354c0ea5?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                  loading="lazy"
                  alt="Photo by Nick Karvounis"
                  className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                />
              </a>

              <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
                <div className="flex flex-col">
                  <a
                    href="/detials"
                    className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
                  >
                    Cool Outfit
                  </a>
                  <div className="">
                    <Rating
                      name="read-only"
                      value={value}
                      readOnly
                      size="small"
                      style={{ color: "black" }}
                      precision={0.5}
                    />
                  </div>
                </div>

                <div className="flex flex-col items-end">
                  <span className="font-bold text-gray-600 lg:text-lg">
                    $29.99
                  </span>
                  <div className=" mt-1">
                    <button
                      onClick={() => {
                        setShow2(!show2);
                        let stro = show1 ? "added to" : "removed from";
                        alert.success(`Fancy Outfit ${stro} watchlist!`);
                      }}
                      className="mb-0.5"
                    >
                      {show2 ? (
                        <FavoriteBorderOutlinedIcon
                          style={{ fontSize: "24px" }}
                        />
                      ) : (
                        <FavoriteOutlinedIcon
                          style={{ color: "#FE5455", fontSize: "24px" }}
                        />
                      )}
                    </button>
                    <button
                      className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
                      onClick={() => {
                        alert.success("Fancy Outfit added to cart!");
                      }}
                    >
                      Add To Cart{" "}
                    </button>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- product - end --> */}

            {/* <!-- product - start --> */}
            <div>
              <a
                href="/detials"
                className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
              >
                <img
                  src="https://images.unsplash.com/photo-1566207274740-0f8cf6b7d5a5?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                  loading="lazy"
                  alt="Photo by Vladimir Fedotov"
                  className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                />
              </a>

              <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
                <div className="flex flex-col">
                  <a
                    href="/detials"
                    className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
                  >
                    Lavish Outfit
                  </a>
                  <div className="">
                    <Rating
                      name="read-only"
                      value={value}
                      readOnly
                      size="small"
                      style={{ color: "black" }}
                      precision={0.5}
                    />
                  </div>
                </div>

                <div className="flex flex-col items-end">
                  <span className="font-bold text-gray-600 lg:text-lg">
                    $49.99
                  </span>
                  <div className="flex items-center mt-1">
                    <button
                      onClick={() => {
                        setShow4(!show4);
                        let stro = show1 ? "added to" : "removed from";
                        alert.success(`Fancy Outfit ${stro} watchlist!`);
                      }}
                      className="mb-0.5"
                    >
                      {show4 ? (
                        <FavoriteBorderOutlinedIcon
                          style={{ fontSize: "24px" }}
                        />
                      ) : (
                        <FavoriteOutlinedIcon
                          style={{ color: "#FE5455", fontSize: "24px" }}
                        />
                      )}
                    </button>
                    <button
                      className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
                      onClick={() => {
                        alert.success("Fancy Outfit added to cart!");
                      }}
                    >
                      Add To Cart{" "}
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div>
              <a
                href="/detials"
                className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
              >
                <img
                  src="https://images.unsplash.com/photo-1552374196-1ab2a1c593e8?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                  loading="lazy"
                  alt="Photo by Austin Wade"
                  className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                />
              </a>

              <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
                <div className="flex flex-col">
                  <a
                    href="/detials"
                    className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
                  >
                    Fancy Outfit
                  </a>

                  <div className="mt-1 flex items-center">
                    <Rating
                      name="read-only"
                      value={value}
                      readOnly
                      size="small"
                      style={{ color: "black" }}
                      precision={0.5}
                    />
                  </div>
                </div>

                <div className="flex flex-col items-end mt-1">
                  <span className="font-bold text-gray-600 lg:text-lg">
                    $19.99
                  </span>
                  <div className="flex items-center mt-1">
                    <button
                      onClick={() => {
                        setShow1(!show1);
                        let stro = show1 ? "added to" : "removed from";
                        alert.success(`Fancy Outfit ${stro} watchlist!`);
                      }}
                      className="mb-0.5"
                    >
                      {show1 ? (
                        <FavoriteBorderOutlinedIcon
                          style={{ fontSize: "24px" }}
                        />
                      ) : (
                        <FavoriteOutlinedIcon
                          style={{ color: "#FE5455", fontSize: "24px" }}
                        />
                      )}
                    </button>
                    <button
                      className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
                      onClick={() => {
                        alert.success("Fancy Outfit added to cart!");
                      }}
                    >
                      Add To Cart{" "}
                    </button>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- product - end --> */}

            {/* <!-- product - start --> */}
            <div>
              <a
                href="/detials"
                className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
              >
                <img
                  src="https://images.unsplash.com/photo-1523359346063-d879354c0ea5?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                  loading="lazy"
                  alt="Photo by Nick Karvounis"
                  className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                />
              </a>

              <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
                <div className="flex flex-col">
                  <a
                    href="/detials"
                    className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
                  >
                    Cool Outfit
                  </a>
                  <div className="">
                    <Rating
                      name="read-only"
                      value={value}
                      readOnly
                      size="small"
                      style={{ color: "black" }}
                      precision={0.5}
                    />
                  </div>
                </div>

                <div className="flex flex-col items-end">
                  <span className="font-bold text-gray-600 lg:text-lg">
                    $29.99
                  </span>
                  <div className=" mt-1">
                    <button
                      onClick={() => {
                        setShow2(!show2);
                        alert.success("Fancy Outfit added to watchlist!");
                      }}
                      className="mb-0.5"
                    >
                      {show2 ? (
                        <FavoriteBorderOutlinedIcon
                          style={{ fontSize: "24px" }}
                        />
                      ) : (
                        <FavoriteOutlinedIcon
                          style={{ color: "#FE5455", fontSize: "24px" }}
                        />
                      )}
                    </button>
                    <button
                      className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
                      onClick={() => {
                        alert.success("Fancy Outfit added to cart!");
                      }}
                    >
                      Add To Cart{" "}
                    </button>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- product - end --> */}

            {/* <!-- product - start --> */}
            <div>
              <a
                href="/detials"
                className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
              >
                <img
                  src="https://images.unsplash.com/photo-1566207274740-0f8cf6b7d5a5?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
                  loading="lazy"
                  alt="Photo by Vladimir Fedotov"
                  className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
                />
              </a>

              <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
                <div className="flex flex-col">
                  <a
                    href="/detials"
                    className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
                  >
                    Lavish Outfit
                  </a>
                  <div className="">
                    <Rating
                      name="read-only"
                      value={value}
                      readOnly
                      size="small"
                      style={{ color: "black" }}
                      precision={0.5}
                    />
                  </div>
                </div>

                <div className="flex flex-col items-end">
                  <span className="font-bold text-gray-600 lg:text-lg">
                    $49.99
                  </span>
                  <div className="flex items-center mt-1">
                    <button
                      onClick={() => {
                        setShow4(!show4);
                        alert.success("Fancy Outfit added to watchlist!");
                      }}
                      className="mb-0.5"
                    >
                      {show4 ? (
                        <FavoriteBorderOutlinedIcon
                          style={{ fontSize: "24px" }}
                        />
                      ) : (
                        <FavoriteOutlinedIcon
                          style={{ color: "#FE5455", fontSize: "24px" }}
                        />
                      )}
                    </button>
                    <button
                      className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
                      onClick={() => {
                        alert.success("Fancy Outfit added to cart!");
                      }}
                    >
                      Add To Cart{" "}
                    </button>
                  </div>
                </div>
              </div>
            </div>
            {/* <!-- product - end --> */}
          </div>
        </div>
      </div>
    </div>
  );
}
