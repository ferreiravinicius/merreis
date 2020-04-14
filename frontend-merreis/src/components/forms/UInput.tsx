import { TextFieldProps } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import TextField from "@material-ui/core/TextField";
import React, { useCallback } from "react";
import { call } from "../../commons/utils";

interface SetterProps {
  action: React.Dispatch<React.SetStateAction<any>>;
  name?: string;
}
interface Props {
  setter?: SetterProps; 
}

type UInputProps = TextFieldProps & Props;

type InputChangeEvent = React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>;

const UInput: React.FC<UInputProps> = ({
  onChange,
  variant,
  setter,
  ...props
}: UInputProps) => {

  const handleState = useCallback((value: any) => {
    if (setter) {
      const { action, name } = setter;
      if (name) {
        action((actual: any) => ({ ...actual, [name]: value }));
      } else {
        action(value);
      }
    }
  }, [setter]);

  const handleChange = (event: InputChangeEvent) => {
    call(handleState, event.target.value);
    call(onChange, event);
  };

  return (
    <FormControl fullWidth>
      <TextField variant="outlined" onChange={handleChange} {...props} />
    </FormControl>
  );
};

export default UInput;
