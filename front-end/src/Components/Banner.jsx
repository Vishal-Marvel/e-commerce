import React from "react";
// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

// import required modules
import { Autoplay, Pagination, Navigation } from "swiper";

export default function Banner() {
  return (
    <>
      <Swiper
        spaceBetween={30}
        centeredSlides={true}
        autoplay={{
          delay: 2500,
          disableOnInteraction: false,
        }}
        pagination={{
          clickable: true,
        }}
        navigation={true}
        modules={[Autoplay, Pagination, Navigation]}
        className="mySwiper"
      >
        <SwiperSlide>
          <img
            className="object-fill w-full slide-height"
            src="https://i.postimg.cc/VvCgsj1H/Pic1.jpg"
            alt="image slide 1"
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            className="object-fill w-full slide-height"
            src="https://i.postimg.cc/bJMLj6Xt/pic2.jpg"
            alt="image slide 2"
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            className="object-fill w-full slide-height"
            src="https://i.postimg.cc/rmPc2LFN/pic3.jpg"
            alt="image slide 3"
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            className="object-fill w-full slide-height"
            src="https://i.postimg.cc/tJsqs4mc/pic4.jpg"
            alt="image slide 4"
          />
        </SwiperSlide>
      </Swiper>
    </>
  );
}