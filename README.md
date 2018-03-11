# mixed_data_gen
Utility for generating linear model data sets

## Usage example

Compile and execute:

    ./gradlew clean assemble installDist
    mkdir -p data
    ./build/install/mixed_data_gen/bin/mixed_data_gen -folder ./data -linear 2 -numeric 2 -text 2 -training 200 -testing 100

Will create a data model with the following features:
- 2 linear features,
- 2 numeric random numbers,
- 2 text random strings.

Outcome will include 3 colunms:
- numeric_outcome - number with Gaussian noise linearly dependent on the first 2 features,
- binary_outcome - 0.0 (if numeric_outcome < 0) or 1.0 (if numeric_outcome >= 0),
- binary_text_outcome - "no" (if numeric_outcome < 0) or "yes" (if numeric_outcome >= 0)

3 files will be written into "data" folder:
- random_31c596a9-ac2c-45fb-9b2a-2a49b3c9d4af_col_9_coef.txt - with 3 regression coefficients (the 2 linear features and intercept)

- random_31c596a9-ac2c-45fb-9b2a-2a49b3c9d4af_col_9_row_100_test.csv.zip - compressed test data subset

- random_31c596a9-ac2c-45fb-9b2a-2a49b3c9d4af_col_9_row_200_train.csv.zip - compressed train data subset

