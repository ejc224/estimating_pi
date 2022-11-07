darts$X <- NULL
darts$power <- c(2, 3, 4, 5, 6, 7, 8)

dev.new()
plot(darts$power, darts$Area, xlab = "Power of 10", ylab = "Estimated area", main = "Estimating pi")
abline(h=3.14, col = "blue")
text(3, 3.143, "y = 3.14", col = "blue")

?plot
