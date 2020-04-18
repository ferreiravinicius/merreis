import { TextFieldProps } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import TextField from "@material-ui/core/TextField";
import React from "react";
import { call } from "../../commons/utils";
import useSetter, { UseSetterProps } from "../../hooks/useSetter";

interface Props {
  setter?: UseSetterProps<any>; 
}

type UInputProps = TextFieldProps & Props;

type InputChangeEvent = React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>;

const UInput: React.FC<UInputProps> = ({
  onChange,
  variant,
  setter,
  ...props
}: UInputProps) => {

  const [ handleSetter ] = useSetter(setter);

  const handleChange = (event: InputChangeEvent) => {
    call(handleSetter, event.target.value);
    call(onChange, event);
  };

  return (
    <FormControl fullWidth>
      <TextField variant="outlined" onChange={handleChange} {...props} />
    </FormControl>
  );
};

export default UInput;
