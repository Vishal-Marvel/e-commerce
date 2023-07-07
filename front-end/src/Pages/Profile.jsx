import { Fragment, useState, useRef } from "react";
import { Dialog, Menu, Transition } from "@headlessui/react";
import {
  CalendarIcon,
  ChartBarIcon,
  FolderIcon,
  HomeIcon,
  InboxIcon,
  MenuAlt2Icon,
  UsersIcon,
  XIcon,
} from "@heroicons/react/outline";
// import { SearchIcon } from "@heroicons/react/solid";
import UserImg from "../assets/profile-user.png";
import EditIcon from "@mui/icons-material/Edit";

const navigation = [
  { name: "Home", href: "/home", icon: HomeIcon, current: true },
  { name: "My Orders", href: "/myorder", icon: UsersIcon, current: false },
  {
    name: "Terms and Conditions",
    href: "/terms",
    icon: FolderIcon,
    current: false,
  },
  {
    name: "Privacy Policy",
    href: "/policy",
    icon: CalendarIcon,
    current: false,
  },
  {
    name: "Shipping Policy",
    href: "/shippolicy",
    icon: InboxIcon,
    current: false,
  },
  {
    name: "Refund Policy",
    href: "/refundpolicy",
    icon: ChartBarIcon,
    current: false,
  },
];
const userNavigation = [
  { name: "Your Profile", href: "#" },
  { name: "Sign out", href: "/" },
];

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Profile() {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const [disabledName1, setDisableFirstName] = useState(true);
  const [disabledName2, setDisableLastName] = useState(true);
  const [disabledEmail, setDisableEmail] = useState(true);
  const [disabledPhone, setDisablePhone] = useState(true);

  const [disabledAddress, setDisableAddress] = useState(true);
  const [disabledCity, setDisableCity] = useState(true);
  const [disabledLandmark, setDisableLandmark] = useState(true);
  const [disabledPin, setDisablePin] = useState(true);

  const [disabledApt, setDisableApt] = useState(true);
  const [disabledState, setDisableState] = useState(true);

  const handleClick1 = () => {
    setDisableFirstName(false);
    ref1.current.focus();
  };
  const handleClick2 = () => {
    setDisableLastName(false);
    ref2.current.focus();
  };
  const handleClick3 = () => {
    setDisableEmail(false);
    ref3.current.focus();
  };
  const handleClick4 = () => {
    setDisablePhone(false);
    ref4.current.focus();
  };

  const handleClick5 = () => {
    setDisableAddress(false);
    ref5.current.focus();
  };
  const handleClick6 = () => {
    setDisableCity(false);
    ref6.current.focus();
  };
  const handleClick7 = () => {
    setDisableLandmark(false);
    ref7.current.focus();
  };
  const handleClick8 = () => {
    setDisablePin(false);
    ref8.current.focus();
  };
  const handleClick9 = () => {
    setDisableApt(false);
    ref9.current.focus();
  };
  const handleClick10 = () => {
    setDisableState(false);
    ref10.current.focus();
  };

  const ref1 = useRef(null);
  const ref2 = useRef(null);
  const ref3 = useRef(null);
  const ref4 = useRef(null);
  const ref5 = useRef(null);
  const ref6 = useRef(null);
  const ref7 = useRef(null);
  const ref8 = useRef(null);
  const ref9 = useRef(null);
  const ref10 = useRef(null);

  return (
    <>
      <div>
        <Transition.Root show={sidebarOpen} as={Fragment}>
          <Dialog
            as="div"
            className="fixed inset-0 flex z-40 md:hidden"
            onClose={setSidebarOpen}
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
              <Dialog.Overlay className="fixed inset-0 bg-gray-600 bg-opacity-75" />
            </Transition.Child>
            <Transition.Child
              as={Fragment}
              enter="transition ease-in-out duration-300 transform"
              enterFrom="-translate-x-full"
              enterTo="translate-x-0"
              leave="transition ease-in-out duration-300 transform"
              leaveFrom="translate-x-0"
              leaveTo="-translate-x-full"
            >
              <div className="relative flex-1 flex flex-col max-w-xs w-full pt-5 pb-4 bg-gray-800">
                <Transition.Child
                  as={Fragment}
                  enter="ease-in-out duration-300"
                  enterFrom="opacity-0"
                  enterTo="opacity-100"
                  leave="ease-in-out duration-300"
                  leaveFrom="opacity-100"
                  leaveTo="opacity-0"
                >
                  <div className="absolute top-0 right-0 -mr-12 pt-2">
                    <button
                      type="button"
                      className="ml-1 flex items-center justify-center h-10 w-10 rounded-full focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white"
                      onClick={() => setSidebarOpen(false)}
                    >
                      <span className="sr-only">Close sidebar</span>
                      <XIcon
                        className="h-6 w-6 text-white"
                        aria-hidden="true"
                      />
                    </button>
                  </div>
                </Transition.Child>
                <div className="flex-shrink-0 flex items-center px-4">
                  {/* <img
                    className="h-8 w-auto"
                    src="https://tailwindui.com/img/logos/workflow-logo-indigo-500-mark-white-text.svg"
                    alt="Workflow"
                  /> */}
                  <p
                    className="inline-flex items-center gap-2 text-xl font-bold text-slate-50 md:text-2xl"
                    aria-label="logo"
                  >
                    Kiyo
                  </p>
                </div>
                <div className="mt-5 flex-1 h-0 overflow-y-auto">
                  <nav className="px-2 space-y-1">
                    {navigation.map((item) => (
                      <a
                        key={item.name}
                        href={item.href}
                        className={classNames(
                          item.current
                            ? "bg-gray-900 text-white"
                            : "text-gray-300 hover:bg-gray-700 hover:text-white",
                          "group flex items-center px-2 py-2 text-base font-medium rounded-md"
                        )}
                      >
                        <item.icon
                          className={classNames(
                            item.current
                              ? "text-gray-300"
                              : "text-gray-400 group-hover:text-gray-300",
                            "mr-4 flex-shrink-0 h-6 w-6"
                          )}
                          aria-hidden="true"
                        />
                        {item.name}
                      </a>
                    ))}
                  </nav>
                </div>
              </div>
            </Transition.Child>
            <div className="flex-shrink-0 w-14" aria-hidden="true">
              {/* Dummy element to force sidebar to shrink to fit close icon */}
            </div>
          </Dialog>
        </Transition.Root>

        {/* Static sidebar for desktop */}
        <div className="hidden md:flex md:w-64 md:flex-col md:fixed md:inset-y-0">
          {/* Sidebar component, swap this element with another sidebar if you like */}
          <div className="flex-1 flex flex-col min-h-0 bg-gray-800">
            <div className="flex items-center h-16 flex-shrink-0 px-4 bg-gray-900">
              {/* <img
                className="h-8 w-auto"
                src="https://tailwindui.com/img/logos/workflow-logo-indigo-500-mark-white-text.svg"
                alt="Workflow"
              /> */}
              <p
                className="inline-flex items-center gap-2 text-xl font-bold text-slate-50 md:text-2xl"
                aria-label="logo"
              >
                Kiyo
              </p>
            </div>
            <div className="flex-1 flex flex-col overflow-y-auto">
              <nav className="flex-1 px-2 py-4 space-y-1">
                {navigation.map((item) => (
                  <a
                    key={item.name}
                    href={item.href}
                    className={classNames(
                      item.current
                        ? "bg-gray-900 text-white"
                        : "text-gray-300 hover:bg-gray-700 hover:text-white",
                      "group flex items-center px-2 py-2 text-sm font-medium rounded-md"
                    )}
                  >
                    <item.icon
                      className={classNames(
                        item.current
                          ? "text-gray-300"
                          : "text-gray-400 group-hover:text-gray-300",
                        "mr-3 flex-shrink-0 h-6 w-6"
                      )}
                      aria-hidden="true"
                    />
                    {item.name}
                  </a>
                ))}
              </nav>
            </div>
          </div>
        </div>
        <div className="md:pl-64 flex flex-col">
          <div className="sticky top-0 z-10 flex-shrink-0 flex h-16 bg-white shadow">
            <button
              type="button"
              className="px-4 border-r border-gray-200 text-gray-500 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-indigo-500 md:hidden"
              onClick={() => setSidebarOpen(true)}
            >
              <span className="sr-only">Open sidebar</span>
              <MenuAlt2Icon className="h-6 w-6" aria-hidden="true" />
            </button>
            <div className="max-w-7xl mx-auto flex items-center px-4 sm:px-6 md:px-8">
              <h1 className="text-2xl font-semibold text-gray-900">
                Dashboard
              </h1>
            </div>

            <div className="flex-1 px-4 flex justify-end">
              {/* <div className="flex-1 flex">
                <form className="w-full flex md:ml-0" action="#" method="GET">
                  <label htmlFor="search-field" className="sr-only">
                    Search
                  </label>
                  <div className="relative w-full text-gray-400 focus-within:text-gray-600">
                    <div className="absolute inset-y-0 left-0 flex items-center pointer-events-none">
                      <SearchIcon className="h-5 w-5" aria-hidden="true" />
                    </div>
                    <input
                      id="search-field"
                      className="block w-full h-full pl-8 pr-3 py-2 border-transparent text-gray-900 placeholder-gray-500 focus:outline-none focus:placeholder-gray-400 focus:ring-0 focus:border-transparent sm:text-sm"
                      placeholder="Search"
                      type="search"
                      name="search"
                    />
                  </div>
                </form>
              </div> */}
              <div className="ml-4 flex items-center md:ml-6">
                {/* <button
                  type="button"
                  className="bg-white p-1 rounded-full text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  <span className="sr-only">View notifications</span>
                  <BellIcon className="h-6 w-6" aria-hidden="true" />
                </button> */}

                {/* Profile dropdown */}
                <Menu as="div" className="ml-3 relative">
                  <div>
                    <Menu.Button className="max-w-xs bg-white flex items-center text-sm rounded-full focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                      <span className="sr-only">Open user menu</span>
                      <img
                        className="h-8 w-8 rounded-full"
                        src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                        alt=""
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
                    <Menu.Items className="origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5 focus:outline-none">
                      {userNavigation.map((item) => (
                        <Menu.Item key={item.name}>
                          {({ active }) => (
                            <a
                              href={item.href}
                              className={classNames(
                                active ? "bg-gray-100" : "",
                                "block px-4 py-2 text-sm text-gray-700"
                              )}
                            >
                              {item.name}
                            </a>
                          )}
                        </Menu.Item>
                      ))}
                    </Menu.Items>
                  </Transition>
                </Menu>
              </div>
            </div>
          </div>

          <main className="flex-1">
            <div className="py-6">
              <div className="max-w-7xl mx-auto px-4 sm:px-6 md:px-8"></div>
              <div className="max-w-7xl mx-auto px-4 sm:px-6 md:px-8">
                {/* Replace with your content */}
                <div className="py-4">
                  {/* <div className="border-4 border-dashed border-gray-200 rounded-lg h-96" />
                   */}
                  <section className="text-gray-600 body-font relative">
                    <div className="px-5 mx-auto">
                      <div className="flex flex-col text-center w-full mb-12">
                        <h1 className="sm:text-3xl text-2xl font-medium title-font mb-4 text-gray-900">
                          Personal Information{" "}
                        </h1>
                        <div className="flex justify-center lg:w-2/3 mx-auto">
                          <img
                            alt="Profile"
                            src={UserImg}
                            className="h-32 w-32"
                          />
                        </div>
                      </div>
                      <div className="lg:w-1/2 md:w-2/3 mx-auto">
                        <div className="flex flex-wrap -m-2">
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="fname"
                                className="leading-7 text-sm text-gray-600"
                              >
                                First Name
                              </label>
                              <input
                                type="text"
                                id="fname"
                                ref={ref1}
                                name="name"
                                readOnly={disabledName1}
                                defaultValue={"Johny"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick1}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="lname"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Last Name
                              </label>
                              <input
                                type="text"
                                id="lname"
                                ref={ref2}
                                name="lname"
                                readOnly={disabledName2}
                                onClick={() => document.getElementById()}
                                defaultValue={"Bastow"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick2}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="email"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Email
                              </label>
                              <input
                                type="email"
                                id="email"
                                ref={ref3}
                                name="email"
                                readOnly={disabledEmail}
                                defaultValue={"example@email.com"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick3}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="tel"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Moblie
                              </label>
                              <input
                                type="tel"
                                id="tel"
                                ref={ref4}
                                name="tel"
                                readOnly={disabledPhone}
                                defaultValue={"+91 8683695273"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick4}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </section>
                  <hr className="h-px my-8 bg-gray-200 border-0 dark:bg-gray-700" />

                  {/* -------------------Adress----------------------------- */}
                  <section className="text-gray-600 body-font relative">
                    <div className="px-5 py-4 mx-auto">
                      <div className="flex flex-col text-center w-full mb-8">
                        <h1 className="sm:text-3xl text-2xl font-medium title-font mb-4 text-gray-900">
                          Shipment Address
                        </h1>
                        <p className="lg:w-2/3 mx-auto leading-relaxed text-base">
                          Whatever cardigan tote bag tumblr hexagon brooklyn
                          asymmetrical gentrify.
                        </p>
                      </div>
                      <div className="lg:last:w-3/4 md:w-2/3 mx-auto">
                        <div className="flex flex-wrap -m-2">
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="apt-no"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Appartment No.
                              </label>
                              <input
                                type="text"
                                id="apt-no"
                                ref={ref9}
                                name="apt-no"
                                readOnly={disabledApt}
                                defaultValue={"147/12, B-Block"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick9}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>

                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="city"
                                className="leading-7 text-sm text-gray-600"
                              >
                                City
                              </label>
                              <input
                                type="text"
                                id="city"
                                ref={ref6}
                                name="city"
                                readOnly={disabledCity}
                                defaultValue={"London"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick6}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="adress"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Address
                              </label>
                              <input
                                type="text"
                                id="adress"
                                ref={ref5}
                                name="adress"
                                readOnly={disabledAddress}
                                defaultValue={"Gunnersbury House, Chapel Hill"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick5}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>

                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="state"
                                className="leading-7 text-sm text-gray-600"
                              >
                                State
                              </label>
                              <input
                                type="text"
                                id="state"
                                ref={ref10}
                                name="state"
                                readOnly={disabledState}
                                defaultValue={"CapeTown"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick10}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>

                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="landmark"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Landmark
                              </label>
                              <input
                                type="text"
                                id="landmark"
                                ref={ref7}
                                name="landmark"
                                readOnly={disabledLandmark}
                                defaultValue={"CapeTower"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick7}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                          <div className="p-2 w-1/2">
                            <div className="relative">
                              <label
                                htmlFor="pin"
                                className="leading-7 text-sm text-gray-600"
                              >
                                Pin code
                              </label>
                              <input
                                type="tel"
                                id="pin"
                                ref={ref8}
                                name="pin"
                                readOnly={disabledPin}
                                defaultValue={"400023"}
                                className="w-full bg-gray-100 bg-opacity-50 rounded border border-gray-300 focus:border-gray-500 focus:bg-white focus:ring-2 focus:ring-gray-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                              />
                              <button
                                className="absolute right-0 mr-2 mt-1.5"
                                onClick={handleClick8}
                              >
                                <EditIcon style={{ fontSize: "large" }} />
                              </button>
                            </div>
                          </div>
                          <div className="p-2 mt-5 w-full">
                            <button
                              className="flex mx-auto text-white bg-black border-0 py-2 px-8 focus:outline-none rounded text-lg"
                              onClick={() => {
                                setDisableAddress(true);
                                setDisableCity(true);
                                setDisableLandmark(true);
                                setDisablePin(true);
                                setDisableApt(true);
                                setDisableState(true);
                                setDisableFirstName(true);
                                setDisableEmail(true);
                                setDisableLastName(true);
                                setDisablePhone(true);
                              }}
                            >
                              Save
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </section>
                </div>
                {/* /End replace */}
              </div>
            </div>
          </main>
        </div>
      </div>
    </>
  );
}
