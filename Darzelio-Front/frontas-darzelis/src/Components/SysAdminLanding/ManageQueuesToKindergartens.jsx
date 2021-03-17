import React from "react";
import {LockButton} from "./LockButton";
import RegisteredChildrenQueueList from "../ChildrenRegistrationQue/RegisteredChildrenQueueList";

export const ManageQueuesToKindergartens = () => {

    return (
        <div className="mt-5 ml-5">
            <h4 className="mb-5">Eilių į darželius tvarkymas</h4>
            <div  className="col-12 shadow-sm border bg-white rounded p-5">
                <LockButton />
            </div>

            <div className="mt-3 border rounded">
                <RegisteredChildrenQueueList />
            </div>
        </div>
    )
}