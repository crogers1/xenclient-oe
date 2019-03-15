# March 15 2019
# For the x64 syncmv it was found that pyicbinn would not compile correctly
# The cause was found to be that libicbinn_resolved.a (which pyibinn links against)
# had not been compiled with -fPIC
# Investigation revealed that libicbinn_resolved.a does get compiled with -fPIC
# The issue is that if you link against other libs that aren't -fPIC compiled,
# the -fPIC flag is in effect ignored
# libicbinn_resolved links against objects from libtirpc, which does not
# normally compile with -fPIC.
# So in order to fix pyicbinn, libtirpc needs to compile with -fPIC
CFLAGS += "-fPIC"
