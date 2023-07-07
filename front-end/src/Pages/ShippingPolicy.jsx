// eslint-disable-next-line no-unused-vars
import React from "react";

export default function ShippingPolicy() {
  return (
    <div>
      <form className="md:m-20  2xl border-4 border-gray-200">
        <div className="md:m-10 sm:m-10 py-24">
          <h1 className="md:text-4xl text-center mb-10 sm:text-2xl font-bold px-2">
            SHIPPING POLICY
          </h1>

          <p className="md:text-lg sm:text-sm px-3 text-justify">
            www.kiyo.com is committed to excellence, and the full satisfaction
            of our customers. www.kiyo.com proudly offers shipping services. Be
            assured we are doing everything in our power to get your order to
            you as soon as possible. Please consider any holidays that might
            impact delivery times. www.kiyo.com also offers same day dispatch.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            1.SHIPPING
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            All orders for our products are processed and shipped out in 4-10
            business days. Orders are not shipped or delivered on weekends or
            holidays. If we are experiencing a high volume of orders, shipments
            may be delayed by a few days. Please allow additional days in
            transit for delivery. If there will be a significant delay in the
            shipment of your order, we will contact you via email.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            2.WRONG ADDRESS DISCLAIMER
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            It is the responsibility of the customers to make sure that the
            shipping address entered is correct. We do our best to speed up
            processing and shipping time, so there is always a small window to
            correct an incorrect shipping address. Please contact us immediately
            if you believe you have provided an incorrect shipping address
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            3.UNDELIVERABLE ORDERS
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            Orders that are returned to us as undeliverable because of incorrect
            shipping information are subject to a restocking fee to be
            determined by us.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            4.LOST/STOLEN PACKAGES
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            www.kiyo.com is not responsible for lost or stolen packages. If your
            tracking information states that your package was delivered to your
            address and you have not received it, please report to the local
            authorities.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            5.RETURN REQUEST DAYS
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            www.kiyo.com allows you to return its item (s) within a period of 7
            days. Kindly be advised that the item (s) should be returned
            unopened and unused.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            6.OUT OF STOCK ITEM PROCESS
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            www.kiyo.com has the following options in the event there are items
            which are out of stock www.kiyo.com Cancel and refund out of stock
            items and ship remaining items in order.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            7.IMPORT DUTY AND TAXES
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            When dealing with www.kiyo.com you have the following options when
            it comes to taxes as well as import duties:You have the option to
            decide whether you want to pre-pay taxes.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            8.ACCEPTANCE
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify">
            By accessing our site and placing an order you have willingly
            accepted the terms of this Shipping Policy.
          </p>

          <h2 className="md:text-3xl mb-2 mt-10 sm:text-lg font-semibold px-2">
            9.CONTACT INFORMATION
          </h2>
          <p className="md:text-lg sm:text-sm px-3 text-justify mb-5">
            In the event you have any questions or comments please reach us via
            the following contacts: Email - kiyo@gmail.com
          </p>

          <input type="checkbox" required />
          <label className="md:mt-5 ml-2">I Accept</label>

          <div className="flex justify-center">
            <button
              type="submit"
              className="rounded-lg bg-green-500 text-white m-auto my-4 p-2"
            >
              Agree
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}
