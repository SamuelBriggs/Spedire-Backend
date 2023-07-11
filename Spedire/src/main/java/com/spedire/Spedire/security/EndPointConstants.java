package com.spedire.Spedire.security;

import java.util.List;

public class EndPointConstants {
    public static final List<String> UNAUTHORIZEDENDPOINTS = List.of("/user", "/login");

    public static final String TEST = "/api/v1/user/test";

    public static List<String> SENDER_ROLE_ENDPOINTS;

    public static List<String> CARRIER_ROLE_ENDPOINTS;

    public static List<String> ADMIN_ROLE_ENDPOINTS;


}
