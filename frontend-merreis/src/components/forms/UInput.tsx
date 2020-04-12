import { TextField, TextFieldProps } from "@material-ui/core";
import React from "react";

type UInputProps = TextFieldProps;

const UInput: React.SFC<UInputProps> = ({ variant, ...props }) => {
  return <TextField variant="outlined" {...props} />;
};

export default UInput;
