import { axiosInstance } from ".";
export const VerifyPhoneNumber = async (phoneNumber) => {
	try {
		const response = await axiosInstance.post(
			"/api/v1/user/verify-number",
			phoneNumber
		);
		return response.data;
	} catch (error) {
		return error.response.data;
	}
};
