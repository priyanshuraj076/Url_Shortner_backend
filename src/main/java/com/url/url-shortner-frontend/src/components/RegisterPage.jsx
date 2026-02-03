import { useForm } from "react-hook-form";
import { motion } from "framer-motion";
import { useState } from "react";
import { Link } from "react-router-dom";

const RegisterPage = () => {
    const [loader,setLoader]=useState(false);
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({
    defaultValues: {
      name: "",
      email: "",
      password: "",
    },
    mode: "onTouched",
  });

  const registerHandler = async (data) => {
    console.log(data);
    reset();
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: 40 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.6 }}
      className="min-h-[calc(100vh-64px)] flex justify-center items-center"
    >
      <motion.form
        onSubmit={handleSubmit(registerHandler)}
        initial={{ scale: 0.9, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ duration: 0.5, delay: 0.2 }}
        className="sm:w-[450px] w-[360px] shadow-lg py-8 sm:px-8 px-4 rounded-md bg-white"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">
          Register Here
        </h1>

        {/* Name */}
        <input
          type="text"
          placeholder="Enter Name"
          className="w-full border px-3 py-2 rounded mb-1"
          {...register("name", { required: "Name is required" })}
        />
        {errors.name && (
          <p className="text-red-500 text-sm mb-3">
            {errors.name.message}
          </p>
        )}

        {/* Email */}
        <input
          type="email"
          placeholder="Enter Email"
        style={{ fontFamily: "system-ui, -apple-system, sans-serif" }}
          className="w-full border px-3 py-2 rounded mb-1"
          {...register("email", { required: "Email is required" })}
        />
        {errors.email && (
          <p className="text-red-500 text-sm mb-3">
            {errors.email.message}
          </p>
        )}

        {/* Password */}
        <input
          type="password"
          placeholder="Enter Password"
          className="w-full border px-3 py-2 rounded mb-1"
          {...register("password", { required: "Password is required" })}
        />
        {errors.password && (
          <p className="text-red-500 text-sm mb-4">
            {errors.password.message}
          </p>
        )}

        <motion.button
        disabled={loader}
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.95 }}
          type="submit"
          className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
        >
          {loader ? "Loading..." : "Register"}
        </motion.button>
        <p className="text-center text-sm text-slate-700 mt-6">
            Already have an account?
            <Link className='font-semibold underline hover:text-black' to="/login"> 
            <span className="text-btnColor">Login</span>
            </Link>
        </p>
      </motion.form>
    </motion.div>
  );
};

export default RegisterPage;
