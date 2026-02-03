import { Link, useLocation } from "react-router-dom";
import { motion } from "framer-motion";

const NavBar = () => {
  const location = useLocation();

  const isActive = (path) => location.pathname === path;

  return (
    <nav className="h-16 bg-gradient-to-r from-blue-600 via-indigo-600 to-purple-600 flex items-center px-6 sticky top-0 z-50">
      <div className="flex justify-between items-center w-full max-w-6xl mx-auto">
        
        {/* Logo */}
        <motion.h1
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
          className="text-white text-2xl font-extrabold tracking-wide cursor-pointer"
        >
          Bitly
        </motion.h1>

        {/* Links */}
        <ul className="flex gap-10 items-center">
          
          {/* Home */}
          <li className="relative">
            <Link
              to="/"
              className={`text-sm font-medium transition-colors duration-200 ${
                isActive("/") ? "text-white" : "text-white/80"
              }`}
            >
              Home
            </Link>

            {isActive("/") && (
              <motion.span
                layoutId="underline"
                className="absolute left-0 -bottom-1 w-full h-[2px] bg-white rounded"
              />
            )}
          </li>

          {/* About */}
          <li className="relative">
            <Link
              to="/about"
              className={`text-sm font-medium transition-colors duration-200 ${
                isActive("/about") ? "text-white" : "text-white/80"
              }`}
            >
              About
            </Link>

            {isActive("/about") && (
              <motion.span
                layoutId="underline"
                className="absolute left-0 -bottom-1 w-full h-[2px] bg-white rounded"
              />
            )}
          </li>

          {/* Sign Up */}
          <motion.li
            whileHover={{ scale: 1.08 }}
            whileTap={{ scale: 0.95 }}
          >
            <Link
              to="/register"
              className="px-4 py-1.0 rounded-full text-sm font-semibold text-white 
              bg-gradient-to-r from-pink-500 to-orange-500 
              hover:from-orange-500 hover:to-pink-500 
              transition-all duration-300 shadow-md"
            >
              Sign Up
            </Link>
          </motion.li>
        </ul>
      </div>
    </nav>
  );
};

export default NavBar;
