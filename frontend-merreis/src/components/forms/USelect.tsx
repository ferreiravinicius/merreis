import {
  Autocomplete,
  AutocompleteProps,
  RenderInputParams,
  UseAutocompleteProps,
} from "@material-ui/lab";
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
    <Autocomplete<T>
      renderInput={(params: RenderInputParams) => (
        <UInput label={label || name} {...params} />
      )}
      {...props}
    />
  );
}

export default USelect;
