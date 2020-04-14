import { FormControl } from "@material-ui/core";
import {
  Autocomplete,
  RenderInputParams,
  UseAutocompleteProps,
} from "@material-ui/lab";
import { useField } from "formik";
import React from "react";
import UInput from "./UInput";

export interface Props {
  name: string;
  label?: string | undefined;
  helperText?: string;
}

export type UAutocompleteProps<T> = UseAutocompleteProps<T> & Props;

function UAutocomplete<T>({
  name,
  label,
  helperText,
  ...props
}: UAutocompleteProps<T>): JSX.Element {
  const [field, { error }, helpers] = useField(name);

  const handleChange = (_: React.ChangeEvent<{}>, value: any) => {
    helpers.setValue(value);
  };

  return (
    <FormControl fullWidth>
      <Autocomplete<T>
        {...props}
        renderInput={(params: RenderInputParams) => (
          <UInput
            name={field.name}
            label={label}
            error={!!error}
            helperText={error || helperText} 
            {...params}
          />
        )}
        onChange={handleChange}
        onBlur={field.onBlur}
        value={field.value}
      />
    </FormControl>
  );
}

export default UAutocomplete;