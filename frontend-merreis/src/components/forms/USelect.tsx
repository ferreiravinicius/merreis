import { FormControl } from "@material-ui/core";
import { Autocomplete, AutocompleteProps, RenderInputParams, UseAutocompleteProps } from "@material-ui/lab";
import React from "react";
import UInput from "./UInput";
export interface Props {
  name: string;
  label?: string;
}

export type USelectProps<T> = Omit<AutocompleteProps<T>, "renderInput"> &
  UseAutocompleteProps<T> &
  Props;

function USelect<T>({ name, label, ...props }: USelectProps<T>): JSX.Element {
  return (
    <FormControl fullWidth>
      <Autocomplete<T>
        renderInput={(params: RenderInputParams) => (
          <UInput name="quiet" {...params} />
        )}
        {...props}
      />
    </FormControl>
  );
}

export default USelect;
