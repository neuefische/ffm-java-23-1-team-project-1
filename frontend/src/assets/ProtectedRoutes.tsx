import {Navigate, Outlet} from "react-router-dom";
import {UserProfile} from "./UserProfileEntities.ts";

type Props = {
    userProfile: UserProfile | undefined
}

export default function ProtectedRoutes(props: Props) {

    const isAuthenticated = props.userProfile !== undefined && props.userProfile.id !== "anonymousUser"

    return(
        isAuthenticated ? <Outlet /> : <Navigate to="/" />
    )
}