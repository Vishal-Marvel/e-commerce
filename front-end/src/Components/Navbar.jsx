import { Fragment, useState } from "react";
import { Dialog, Popover, Transition } from "@headlessui/react";
import {
  MenuIcon,
  SearchIcon,
  ShoppingBagIcon,
  XIcon,
  HeartIcon,
  UserCircleIcon,
} from "@heroicons/react/outline";
import Search from "./DisplaySearch";

const navigation = {
  pages: [
    { name: "Home", href: "/home" },
    { name: "Products", href: "/products" },
    { name: "Contact", href: "/contact" },
    { name: "About", href: "/about" },
  ],
};

export default function NavBar(props) {
  const [open, setOpen] = useState(false);

  const [search, setSearch] = useState(false);

  return (
    <div className="bg-black">
      {/* Mobile menu */}
      <Transition.Root show={open} as={Fragment}>
        <Dialog
          as="div"
          className="fixed inset-0 flex z-40 lg:hidden"
          onClose={setOpen}
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
            enterFrom="-translate-x-full"
            enterTo="translate-x-0"
            leave="transition ease-in-out duration-300 transform"
            leaveFrom="translate-x-0"
            leaveTo="-translate-x-full"
          >
            <div className="relative max-w-xs w-full bg-black shadow-xl pb-12 flex flex-col overflow-y-auto">
              <div className="px-4 pt-5 pb-2 flex">
                <button
                  type="button"
                  className="-m-2 p-2 rounded-md inline-flex items-center justify-center text-gray-400"
                  onClick={() => setOpen(false)}
                >
                  <span className="sr-only">Close menu</span>
                  <XIcon className="h-6 w-6" aria-hidden="true" />
                </button>
              </div>

              <div className="border-t border-gray-200 py-6 px-4 space-y-6">
                {navigation.pages.map((page) => (
                  <div key={page.name} className="flow-root">
                    <a
                      href={page.href}
                      className="-m-2 p-2 block font-medium text-gray-200"
                    >
                      {page.name}
                    </a>
                  </div>
                ))}
              </div>

              {!props.home && (
                <div className="border-t border-gray-400 py-6 px-4 space-y-6">
                  <div className="flow-root">
                    <a
                      href="/signin"
                      className="-m-2 p-2 block font-medium text-gray-400"
                    >
                      Sign in
                    </a>
                  </div>
                  <div className="flow-root">
                    <a
                      href="/sigup"
                      className="-m-2 p-2 block font-medium text-gray-400"
                    >
                      Create account
                    </a>
                  </div>
                </div>
              )}
            </div>
          </Transition.Child>
        </Dialog>
      </Transition.Root>

      <header className="relative bg-black">
        <nav
          aria-label="Top"
          className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8"
        >
          <div className="">
            <div className="h-16 flex items-center">
              <button
                type="button"
                className="bg-black p-2 rounded-md text-gray-400 lg:hidden"
                onClick={() => setOpen(true)}
              >
                <span className="sr-only">Open menu</span>
                <MenuIcon className="h-6 w-6" aria-hidden="true" />
              </button>

              {/* Logo */}
              <div className="ml-4 flex lg:ml-0">
                <a
                  href="/"
                  className="inline-flex items-center gap-2 text-xl font-bold text-slate-50 md:text-2xl"
                  aria-label="logo"
                >
                  Kiyo
                </a>
              </div>

              {/* <Search /> */}

              {/* Flyout menus */}
              <Popover.Group className="hidden lg:ml-8 lg:block lg:self-stretch">
                <div className="h-full flex space-x-8">
                  {navigation.pages.map((page) => (
                    <a
                      key={page.name}
                      href={page.href}
                      className="flex items-center text-sm font-medium text-gray-400 hover:text-white"
                    >
                      {page.name}
                    </a>
                  ))}
                </div>
              </Popover.Group>

              <div className="ml-auto flex items-center">
                {!props.home && (
                  <div className="hidden lg:flex lg:flex-1 lg:items-center lg:justify-end lg:space-x-6">
                    <a
                      href="/signin"
                      className="text-sm font-medium text-gray-400 hover:text-white"
                    >
                      Sign in
                    </a>
                    <span className="h-6 w-px bg-gray-900" aria-hidden="true" />
                    <a
                      href="/signup"
                      className="text-sm font-medium text-gray-400 hover:text-white"
                    >
                      Create account
                    </a>
                  </div>
                )}

                {props.home && (
                  <div className="flex lg:ml-6">
                    <a
                      href="/profile"
                      className="p-2 text-gray-400 hover:text-gray-500"
                    >
                      <span className="sr-only">Account</span>
                      <UserCircleIcon className="w-6 h-6" aria-hidden="true" />
                    </a>
                  </div>
                )}

                {/* Search */}
                <div
                  className="flex lg:ml-4"
                  id="search-toggle search-icon cursor-pointer pl-6"
                  onClick={() => setSearch(!search)}
                >
                  <a href="#" className="p-2 text-gray-400 hover:text-gray-500">
                    <span className="sr-only">Search</span>
                    <SearchIcon className="w-6 h-6" aria-hidden="true" />
                  </a>
                </div>

                <div className="ml-4 flex lg:ml-4">
                  <a
                    href="/wishlist"
                    className="p-2 text-gray-400 hover:text-gray-500"
                  >
                    <span className="sr-only">Fav</span>
                    <HeartIcon className="w-6 h-6" aria-hidden="true" />
                  </a>
                </div>

                {/* Cart */}
                <div className="ml-4 flow-root lg:ml-6">
                  <a href="/Cart" className="group -m-2 p-2 flex items-center">
                    <ShoppingBagIcon
                      className="flex-shrink-0 h-6 w-6 text-gray-400 group-hover:text-gray-500"
                      aria-hidden="true"
                    />
                    <span className="ml-2 text-sm font-medium text-gray-700 group-hover:text-gray-800">
                      0
                    </span>
                    <span className="sr-only">items in cart, view bag</span>
                  </a>
                </div>
              </div>
            </div>
          </div>
          {search && (
            <Search />
            // <div
            //   className="relative w-full bg-white shadow-xl"
            //   id="search-content"
            // >
            //   <div className="mx-auto flex absolute z-10 w-full text-black">
            //     <input
            //       id="searchfield"
            //       type="search"
            //       placeholder="Search..."
            //       autoFocus="autofocus"
            //       className="w-full text-grey-800 transition focus:outline-none focus:ring-0 focus:border-transparent p-2 appearance-none border-r-0 leading-normal text-xl lg:text-2xl"
            //     />
            //     <button className="bg-white p-5 border border-solid border-black border-l-0">
            //       <SearchIcon className="w-6 h-6" aria-hidden="true" />
            //     </button>
            //   </div>
            // </div>
          )}
        </nav>
      </header>
    </div>
  );
}
