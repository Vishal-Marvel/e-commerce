// eslint-disable-next-line no-unused-vars
import React from "react";
import "../index.css";
import prod from "../assets/products.png";
import custs from "../assets/customerservice.png";
import about from "../assets/aboutus.png";
import contact from "../assets/contact.png";
import help from "../assets/help.png";
import { isMobile } from "react-device-detect";

import { useState, useEffect } from "react";

function Footer() {
  const [deviceType, setDeviceType] = useState(false);

  useEffect(() => {
    if (isMobile == true) {
      setDeviceType(true);
    } else {
      setDeviceType(false);
    }
  }, []);
  const [value, setValue] = React.useState(0);

  return (
    <div className="bg-black">
      {true && (
        <footer className="mx-auto max-w-screen-2xl px-4 md:px-8">
          <div className="mb-16 grid grid-cols-2 gap-12 pt-10 md:grid-cols-4 lg:grid-cols-6 lg:gap-8 lg:pt-12">
            <div className="col-span-full lg:col-span-2">
              {/* <!-- logo - start  */}
              <div className="mb-4 lg:-mt-2">
                <a
                  href="/"
                  className="inline-flex items-center gap-2 text-xl font-bold text-slate-500 md:text-2xl"
                  aria-label="logo"
                >
                  Kiyo
                </a>
              </div>
              {/* <!-- logo - end  */}

              <p className="mb-6 text-slate-500 sm:pr-8">
                Filler text is dummy text which has no meaning however looks
                very similar to real text.
              </p>

              {/* <!-- social - start  */}
              <div className="flex gap-4">
                <a
                  href="#"
                  target="_blank"
                  className="text-slate-500 transition duration-100 hover:text-slate-500 active:text-slate-500"
                >
                  <svg
                    className="h-5 w-5"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z" />
                  </svg>
                </a>

                <a
                  href="#"
                  target="_blank"
                  className="text-slate-500 transition duration-100 hover:text-slate-500 active:text-slate-500"
                >
                  <svg
                    className="h-5 w-5"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    xmlns="https://icons8.com/icon/8818/facebook"
                  >
                    <path d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z" />
                  </svg>
                </a>

                <a
                  href="#"
                  target="_blank"
                  className="text-slate-500 transition duration-100 hover:text-slate-500 active:text-slate-500"
                >
                  <svg
                    className="h-5 w-5"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-11h3v11zm-1.5-12.268c-.966 0-1.75-.79-1.75-1.764s.784-1.764 1.75-1.764 1.75.79 1.75 1.764-.783 1.764-1.75 1.764zm13.5 12.268h-3v-5.604c0-3.368-4-3.113-4 0v5.604h-3v-11h3v1.765c1.396-2.586 7-2.777 7 2.476v6.759z" />
                  </svg>
                </a>

                <a
                  href="#"
                  target="_blank"
                  className="text-slate-500 transition duration-100 hover:text-slate-500 active:text-slate-500"
                >
                  <svg
                    className="h-5 w-5"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 448 512"
                    id="IconChangeColor"
                    height="24"
                    width="24"
                  >
                    <path
                      d="M400 32H48A48 48 0 0 0 0 80v352a48 48 0 0 0 48 48h137.25V327.69h-63V256h63v-54.64c0-62.15 37-96.48 93.67-96.48 27.14 0 55.52 4.84 55.52 4.84v61h-31.27c-30.81 0-40.42 19.12-40.42 38.73V256h68.78l-11 71.69h-57.78V480H400a48 48 0 0 0 48-48V80a48 48 0 0 0-48-48z"
                      id="mainIconPathAttribute"
                      fill="#64748b"
                    ></path>
                  </svg>
                </a>
              </div>
              {/* <!-- social - end  */}
            </div>

            {/* <!-- nav - start  */}
            <div>
              <div className="mb-2">
                <img src={prod} alt="product" />
              </div>
              <div className="mb-4 font-bold uppercase tracking-widest text-white">
                Products
              </div>

              <nav className="flex flex-col gap-4">
                <div>
                  <a
                    href="/about"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Overview
                  </a>
                </div>

                <div>
                  <a
                    href="#"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Solutions
                  </a>
                </div>

                <div>
                  <a
                    href="/refundpolicy"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Refund Policy
                  </a>
                </div>
              </nav>
            </div>
            {/* <!-- nav - end  */}

            {/* <!-- nav - start  */}
            <div>
              <div className="mb-2">
                <img src={about} alt="about" />
              </div>
              <div className="mb-4 font-bold uppercase tracking-widest text-white">
                Company
              </div>

              <nav className="flex flex-col gap-4">
                <div>
                  <a
                    href="/about"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    About
                  </a>
                </div>

                <div>
                  <a
                    href="/about"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Jobs
                  </a>
                </div>

                <div>
                  <a
                    href="/about"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Blog
                  </a>
                </div>
              </nav>
            </div>
            {/* <!-- nav - end  */}

            {/* <!-- nav - start  */}
            <div>
              <div className="mb-2">
                <img src={contact} alt="contact" />
              </div>
              <div className="mb-4 font-bold uppercase tracking-widest text-white">
                Support
              </div>

              <nav className="flex flex-col gap-4">
                <div>
                  <a
                    href="/contact"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Contact
                  </a>
                </div>

                <div>
                  <a
                    href="/contact"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Documentation
                  </a>
                </div>

                <div>
                  <a
                    href="/faq"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    FAQ
                  </a>
                </div>
              </nav>
            </div>
            {/* <!-- nav - end  */}

            {/* <!-- nav - start  */}
            <div>
              <div className="mb-2">
                <img src={help} alt="help" />
              </div>
              <div className="mb-4 font-bold uppercase tracking-widest text-white">
                Legal
              </div>

              <nav className="flex flex-col gap-4">
                <div>
                  <a
                    href="/terms"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Terms of Service
                  </a>
                </div>

                <div>
                  <a
                    href="/policy"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Privacy Policy
                  </a>
                </div>

                <div>
                  <a
                    href="/shippolicy"
                    className="text-slate-500 transition duration-100 hover:text-white active:text-white"
                  >
                    Shipping Policy
                  </a>
                </div>
              </nav>
            </div>
            {/* <!-- nav - end  */}
          </div>

          <div className="border-t border-slate-500 py-8 text-center text-sm text-slate-500">
            © 2023 - Present Kiyo. All rights reserved.
          </div>
        </footer>
      )}
      {deviceType &&
        // For Mobile view
        console.log("pp")}
    </div>
  );
}
export default Footer;
