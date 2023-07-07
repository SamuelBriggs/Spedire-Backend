import React from "react";
//import "../../../App.css";
import { toast } from "react-hot-toast";
import "../styles/index.css";
import { useState } from "react";
import Logo from "../../assets/verifyPhoneNumber/map-icon.svg";
import { VerifyPhone } from "../../apicalls/users";

const ForgotPassword = () => {
	const [phoneNumber, setPhoneNumber] = useState("");
	const [error, setError] = useState("");

	const checkPhoneNumber = (phoneNumber) => {
		const phoneNumberRegex =
			/((^090)([23589]))|((^070)([1-9]))|((^080)([2-9]))|((^081)([0-9]))(\d{7})/;
		return phoneNumberRegex.test(phoneNumber);
	};
	const handleSubmit = async (e) => {
		setError("");
		e.preventDefault();
		console.log(checkPhoneNumber(phoneNumber));

		if (!checkPhoneNumber(phoneNumber)) {
			setError("Invalid phone number");
		}
		try {
			const response = await VerifyPhone(phoneNumber);
			if (response.success) {
				toast.success(response.message);
				setPhoneNumber("");
			}
		} catch (err) {
			toast.error(err.message);
		}
	};
	return (
		<div>
			<div className="central">
				<div className="topContainer">
					<div>
						<img src={Logo} className="img" alt="Img" />
					</div>
					<div className="spedireContainer">
						{" "}
						<h1 className="spedire">Spedire</h1>{" "}
					</div>
				</div>

				{/* <div className='button'>
            <button type='button' className='backButton'>BACK</button>
        </div> */}
			</div>
			<div className="formContainer">
				<form onSubmit={handleSubmit}>
					<div className="email">
						<center>
							<div className="forgotPassword">
								<h2 className="forgotPasswordText">VERIFY PHONE NUMBER</h2>
							</div>
							<br />
							<br />
						</center>
						{error ? <p className="error">{error}</p> : ""}

						<input
							type="text"
							className="input"
							name="phoneNumber"
							placeholder="Enter phone number"
							value={phoneNumber}
							onChange={(e) => {
								setPhoneNumber(e.target.value);
							}}
							required
						/>
					</div>
					<br />
					<br />

					<button type="submit" className="submit">
						SUBMIT
					</button>
				</form>
				<h6 className="option">
					Already have an account?&rarr;{" "}
					<span style={{ color: "#5c7ae8" }}>Login</span>
				</h6>
			</div>
		</div>
	);
};

export default ForgotPassword;
