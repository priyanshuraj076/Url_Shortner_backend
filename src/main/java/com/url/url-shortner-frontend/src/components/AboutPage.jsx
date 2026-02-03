import React from "react";
import { FaLink, FaShareAlt, FaEdit, FaChartLine } from "react-icons/fa";

const AboutPage = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 px-5 sm:px-8 lg:px-14 py-10">
      
      {/* Header Section */}
      <div className="max-w-5xl mx-auto mb-12">
        <h1 className="text-4xl sm:text-5xl font-extrabold text-slate-800 mb-4">
          About <span className="text-blue-600">Bitly</span>
        </h1>
        <p className="text-gray-700 text-lg leading-relaxed">
          Bitly simplifies URL shortening for efficient sharing. Easily
          generate, manage, and track your shortened links with speed,
          security, and powerful analytics — all in one place.
        </p>
      </div>

      {/* Feature Cards */}
      <div className="max-w-5xl mx-auto grid gap-6 sm:grid-cols-2">
        
        {/* Card 1 */}
        <div className="bg-white rounded-2xl shadow-md p-6 flex gap-4 hover:shadow-xl transition">
          <FaLink className="text-blue-500 text-4xl" />
          <div>
            <h2 className="text-2xl font-semibold text-slate-800 mb-1">
              Simple URL Shortening
            </h2>
            <p className="text-gray-600 leading-relaxed">
              Create short, memorable URLs in just a few clicks with an intuitive
              and user-friendly interface.
            </p>
          </div>
        </div>

        {/* Card 2 */}
        <div className="bg-white rounded-2xl shadow-md p-6 flex gap-4 hover:shadow-xl transition">
          <FaShareAlt className="text-green-500 text-4xl" />
          <div>
            <h2 className="text-2xl font-semibold text-slate-800 mb-1">
              Powerful Analytics
            </h2>
            <p className="text-gray-600 leading-relaxed">
              Track clicks, locations, and referral sources with detailed
              analytics to optimize your reach.
            </p>
          </div>
        </div>

        {/* Card 3 */}
        <div className="bg-white rounded-2xl shadow-md p-6 flex gap-4 hover:shadow-xl transition">
          <FaEdit className="text-purple-500 text-4xl" />
          <div>
            <h2 className="text-2xl font-semibold text-slate-800 mb-1">
              Enhanced Security
            </h2>
            <p className="text-gray-600 leading-relaxed">
              Your links are protected with strong encryption and modern
              security practices.
            </p>
          </div>
        </div>

        {/* Card 4 */}
        <div className="bg-white rounded-2xl shadow-md p-6 flex gap-4 hover:shadow-xl transition">
          <FaChartLine className="text-red-500 text-4xl" />
          <div>
            <h2 className="text-2xl font-semibold text-slate-800 mb-1">
              Fast & Reliable
            </h2>
            <p className="text-gray-600 leading-relaxed">
              Experience lightning-fast redirects and high availability backed
              by reliable infrastructure.
            </p>
          </div>
        </div>

      </div>
    </div>
  );
};

export default AboutPage;
