import { FormControl, TextField } from "@material-ui/core";
import {
  Autocomplete,
  RenderInputParams,
  UseAutocompleteProps,
} from "@material-ui/lab";
import React from "react";
import UInput from "./UInput";

export interface Props {
  label?: string;
}

export type UAutocompleteProps<T> = UseAutocompleteProps<T> & Props;

const UAutocomplete = <T extends {}>({
  label,
  ...props
}: UAutocompleteProps<T>): JSX.Element => {
  return (
    <FormControl fullWidth>
      <Autocomplete<T>
        renderInput={(params: RenderInputParams) => (
          <UInput label={label} {...params} />
        )}
        {...props}
      />
    </FormControl>
  );
};

export default UAutocomplete;
