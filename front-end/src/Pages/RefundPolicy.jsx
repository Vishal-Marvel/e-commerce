// eslint-disable-next-line no-unused-vars
import React from "react";
import NavBar from "../Components/Navbar";
import Footer from "../Components/Footer";

export default function RefundPolicy() {
  return (
    <div>
      <NavBar />
      <div className="mx-auto my-16 w-3/4 border-4 border-gray-200">
        <h1 className="md:text-4xl text-center mb-10 mt-8 sm:text-2xl font-bold px-2">
          Refund policy
        </h1>
        <p className="md:text-lg sm:text-sm px-3 text-justify">
          We do not provide refunds after the product is shipped, which you
          acknowledge prior to purchasing any product on the Website. Please
          make sure that you&#8217;ve carefully read product description before
          making a purchase.
        </p>
        <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
          Contacting us
        </h2>
        <p className="md:text-lg sm:text-sm px-3 text-justify">
          If you have any questions, concerns, or complaints regarding this
          refund policy, we encourage you to contact us using the details below:
        </p>
        <p className="md:text-lg sm:text-sm px-3 text-justify">
          <a href="&#109;&#097;&#105;&#108;&#116;&#111;&#058;&#107;iy&#111;&#64;g&#109;&#97;&#105;&#108;&#46;&#99;om">
            &#107;&#105;y&#111;&#64;g&#109;&#97;&#105;l&#46;&#99;o&#109;
          </a>
        </p>
        <p className="md:text-lg sm:text-sm px-3 text-justify">
          This document was last updated on June 5, 2023
        </p>
        <p className="md:text-lg sm:text-sm px-3 text-justify">
          <a
            href="https://www.websitepolicies.com/refund-policy-generator?via=madewithbadge"
            target="blank"
          >
            <img
              width="200"
              height="25"
              alt="Made with WebsitePolicies refund policy generator"
              src="https://cdn.websitepolicies.io/img/badge.png"
              srcSet="https://cdn.websitepolicies.io/img/badge_2x.png 2x"
            />
          </a>
        </p>
      </div>
      <Footer />
    </div>
  );
}
