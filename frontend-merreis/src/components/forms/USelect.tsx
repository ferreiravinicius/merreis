import { TextField } from "@material-ui/core";
import { Autocomplete, AutocompleteProps, RenderInputParams, UseAutocompleteProps } from "@material-ui/lab";
import React from "react";

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
        <TextField label={label || name} variant="outlined" {...params} />
      )}
      {...props}
    />
  );
};

export default USelect;
