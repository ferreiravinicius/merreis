import { Autocomplete, AutocompleteProps, RenderInputParams, UseAutocompleteProps } from "@material-ui/lab";
import React from "react";
import UInput from "./UInput";

export interface Option<T> {
  item: T;
  label: string;
}

export interface Props {
  name: string;
  label?: string;
}

export type USelectProps<T> = Omit<AutocompleteProps<T>, "renderInput"> &
  UseAutocompleteProps<T> &
  Props;

const USelect: React.FC<USelectProps<Option<any>>> = ({ name, label, ...props }) => {

  return (
    <Autocomplete
      getOptionLabel={(option: Option<any>) => option.label}
      renderOption={(option: Option<any>) => option.label}
      renderInput={(params: RenderInputParams) => (
        <UInput label={label || name} {...params} />
      )}
      {...props}
    />
  );
};

export default USelect;
