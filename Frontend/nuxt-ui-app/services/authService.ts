import axios from 'axios';

const API_URL = 'http://localhost:8080/users';

export const login = async (username: string, password: string) => {
    try {
        const response = await axios.post(`${API_URL}/login`, { username, password });
        return response.data; // This could be the JWT token or any other response
    } catch (error) {
        console.error('Error during login:', error);
        throw error;
    }
};

export const register = async (username: string, email: string, password: string, accountType: string) => {
    try {
        const response = await axios.post(`${API_URL}/register`, { username, email, password });
        return response.data;
    } catch (error) {
        console.error('Error during registration:', error);
        throw error;
    }
};
