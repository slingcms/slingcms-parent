const gulp        = require('gulp');
const sass        = require('gulp-sass');
const header      = require('gulp-header');
const cleanCSS   = require('gulp-clean-css');
const rename =  require('gulp-rename');
var concatCss = require('gulp-concat-css');

function srcDir(sub) {
    return `./src/${sub}`;
}
function distDir(sub) {
    return `./dist/${sub}`;
}

gulp.task('starter-styles', () => gulp.src(srcDir('scss/*.scss'))
    .pipe(sass().on('error', sass.logError))
    .pipe(cleanCSS())
    .pipe(concatCss('bundle.css'))
    .pipe(header(apache2License))
    .pipe(gulp.dest(distDir('jcr_root/content/starter/css'))));

gulp.task('starter-assets', () => gulp.src(srcDir('img/*'))
    .pipe(gulp.dest(distDir('jcr_root/content/starter/img'))));

gulp.task('starter-fonts', () => gulp.src([srcDir('fonts/*')])
    .pipe(gulp.dest(distDir('jcr_root/content/starter/fonts'))));


gulp.task('starter-logo', () => gulp.src(srcDir('img/sling-logo.svg'))
    .pipe(gulp.dest(distDir('jcr_root/content/starter'))));

gulp.task('default', gulp.series('starter-styles', 'starter-assets', 'starter-fonts', 'starter-logo'));

